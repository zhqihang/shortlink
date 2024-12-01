package com.qihang.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.shortlink.project.common.convention.exception.ClientException;
import com.qihang.shortlink.project.common.convention.exception.ServiceException;
import com.qihang.shortlink.project.common.enums.VaildDateTypeEnum;
import com.qihang.shortlink.project.dao.entity.ShortLinkDO;
import com.qihang.shortlink.project.dao.mapper.ShortLinkMapper;
import com.qihang.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.qihang.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.qihang.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.qihang.shortlink.project.service.ShortLinkService;
import com.qihang.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 短链接接口实现层
 * @author: zhqihang
 * @date: 2024/10/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService{

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .fullShortUrl(fullShortUrl)
                .build();
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException e) {
            // 误判的短链接处理
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getShortUri, shortLinkSuffix);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO != null) {
                log.warn("短链接：{} 重复入库", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        // 加入布隆过滤器
        shortUriCreateCachePenetrationBloomFilter.add(shortLinkSuffix);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Override
    public void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        // 查询需要更改的短链接
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid()) // 分组标识
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getDelFlag, 0)
                .eq(ShortLinkDO::getEnableStatus, 0);
        ShortLinkDO hasShortLink = baseMapper.selectOne(queryWrapper);
        // 判断是否为空
        if (hasShortLink == null) {
            throw new ClientException("短链接记录不存在");
        }
        // 创建短链接对象
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                // 不会产生修改的参数 从原始短链接获取
                .domain(hasShortLink.getDomain()) // 域名
                .shortUri(hasShortLink.getShortUri()) // 短链接
                .clickNum(hasShortLink.getClickNum()) // 点击量
                .favicon(hasShortLink.getFavicon()) // 图标
                .createdType(hasShortLink.getCreatedType()) // 创建类型
                // 需要修改的参数 从修改请求参数获取
                .originUrl(requestParam.getOriginUrl()) // 原始链接
                // .fullShortUrl(requestParam.getFullShortUrl()) // 完整短链接
                .gid(requestParam.getGid()) // 分组标识
                .describe(requestParam.getDescribe()) // 描述
                .validDateType(requestParam.getValidDateType()) // 有效期类型
                .validDate(requestParam.getValidDate()) // 有效期时间
                .build();

        // 对比分组标识
        if (Objects.equals(hasShortLink.getGid(), requestParam.getGid())) {
            // 同一个分组 则只进行更新
            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl()) // 完整短链接
                    .eq(ShortLinkDO::getGid, requestParam.getGid())
                    .eq(ShortLinkDO::getDelFlag, 0)
                    .eq(ShortLinkDO::getEnableStatus, 0)
                    // 如果是永久有效 更改 有效期时间 为空
                    .set(Objects.equals(requestParam.getValidDateType(), VaildDateTypeEnum.PERMANENT.getType()), ShortLinkDO::getValidDate, null);
            baseMapper.update(shortLinkDO, updateWrapper); // 更新
        } else {
            // 不同分组 则 先删除再更新
            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl()) // 完整短链接
                    .eq(ShortLinkDO::getGid, hasShortLink.getGid()) // 原始 gid 查
                    .eq(ShortLinkDO::getDelFlag, 0) // 删除标识 未删除
                    .eq(ShortLinkDO::getEnableStatus, 0); // 启用标识 未启用
            baseMapper.delete(updateWrapper);
            baseMapper.insert(shortLinkDO);
        }

    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        // 分页查询
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0) // 启用的短链接
                .eq(ShortLinkDO::getDelFlag, 0)
                .orderByDesc(ShortLinkDO::getCreateTime);
        ShortLinkPageReqDTO resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        // 转换为bean
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam) {
        QueryWrapper<ShortLinkDO> queryWrapper = Wrappers.query(new ShortLinkDO())
                .select("gid as gid, count(*) as shortLinkCount")
                .in("gid", requestParam)
                .eq("enable_status", 0)
                .groupBy("gid");
        List<Map<String, Object>> shortLinkDOList = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.copyToList(shortLinkDOList, ShortLinkGroupCountQueryRespDTO.class);
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String shortUri;
        // 存在哈希冲突，需要重试机制
        int customGenerateCount = 0;
        while (true) {
            if (customGenerateCount > 10) {
                throw  new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl();
            originUrl += System.currentTimeMillis(); // 降低冲突概率
            shortUri = HashUtil.hashToBase62(originUrl);
            // 走布隆过滤器 替代每次查询数据库
            if (!shortUriCreateCachePenetrationBloomFilter.contains(shortUri)) {
                break;
            }
            customGenerateCount++;
        }
        return shortUri;
    }
}
