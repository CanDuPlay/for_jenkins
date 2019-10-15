package com.zklcsoftware.common.web;

import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import com.zklcsoftware.basic.repository.search.DynamicSpecifications;
import com.zklcsoftware.basic.repository.search.SearchFilter;
import com.zklcsoftware.basic.repository.search.SortItem;
import com.zklcsoftware.basic.repository.search.SortObject;
import com.zklcsoftware.basic.util.ServletUtils;
import com.zklcsoftware.basic.web.BaseController;
import lombok.extern.slf4j.Slf4j;

/**
 * @author administrator
 * @date 2019-3-18
 */
@Slf4j
public class ExtBaseController  extends BaseController{
	/**
	 * 获取当前登录用户MAP
	 * @return 已登录返回用户信息对象，未登录返回<code>null</code>。
	 */
	protected Map getLoginUser() {
		log.debug("getLoginUser() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		log.debug("getLoginUser() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return (Map) uptoken.getDetails();
	}
	
	/**
	 * 获取当前用户标示
	 * 
	 */
	protected String getUserGuid() {
//		return String.valueOf(("2c918097650ced8b01650d1045460017"));
		return String.valueOf(("2c91808569dd62030169e122abef0014"));
	}
	
	
	/**
	 * 获取当前登录用户名
	 * 
	 */
	protected String getUserLoginName() {
		log.debug("getLoginUser() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		Map uMap=(Map) uptoken.getDetails();
		log.debug("getLoginUser() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return String.valueOf(uMap.get("username"));
	}
	
	/**
	 * 获取当前用户姓名
	 * 
	 */
	protected String getUName() {
		return String.valueOf(("name"));
	}
	
	/**
	 * 获取当前用户学校标示
	 * 
	 */
	protected String getUserSchoolGuid() {
		log.debug("getLoginUser() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		Map uMap=(Map) uptoken.getDetails();
		log.debug("getLoginUser() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return String.valueOf(uMap.get("schoolGuid"));
	}
	
	/**
	 * 获取当前用户学区标示
	 * 
	 */
	protected String getUserDistrictGuid() {
		log.debug("getLoginUser() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		Map uMap=(Map) uptoken.getDetails();
		log.debug("getLoginUser() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return String.valueOf(uMap.get("districtGuid"));
	}
	
	/**
	 * 获取当前用户学区标示
	 * 
	 */
	protected String getUserClassGuid() {
		log.debug("getLoginUser() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		Map uMap=(Map) uptoken.getDetails();
		log.debug("getLoginUser() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return String.valueOf(uMap.get("classGuid"));
	}
	
	/**
	 * 获取当前用户类型
	 * 
	 */
	protected String getUserType() {
		log.debug("getLoginUser() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		Map uMap=(Map) uptoken.getDetails();
		log.debug("getLoginUser() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return String.valueOf(uMap.get("userType"));
	}
	
	/**
	 * 获取当前用户类型
	 * 
	 */
	protected String getUsePost() {
		log.debug("getLoginUser() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		Map uMap=(Map) uptoken.getDetails();
		log.debug("getLoginUser() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return String.valueOf(uMap.get("post"));
	}
	
	/**
	 * 获取当前用户角色权限
	 * 
	 */
	protected String getUserRole() {
		log.debug("getUserRole() - start"); //$NON-NLS-1$
		OAuth2Authentication oAuth2Authentication=(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken uptoken=(UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
		Map uMap=(Map) uptoken.getDetails();
		log.debug("getUserRole() - end, user={}", oAuth2Authentication); //$NON-NLS-1$
		return String.valueOf(uMap.get("role"));
	}
	
	
	/**
     * 获取当前用户范围
     * 
     */
    protected String getUserScope() {
        return String.valueOf(("101007"));
    }
	
	/**
     * 通过ServletRequest构建动态查询参数
     * @param request
     * @return
     */
    protected Specification buildSpecification(ServletRequest request){
        Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "query_");
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification spec = DynamicSpecifications.bySearchFilter(filters.values());
        return spec;
    }
    
    
    /**
     * 构建排序对象
     * @param sorts
     * @return
     */
    protected Sort buildSort(SortObject sorts){
        Sort sort = null;
        if (sorts.getSorts() != null) {
            int len = sorts.getSorts().size();
            for (int i = 0; i < len; i++) {
                SortItem item = sorts.getSorts().get(i);
                if (StringUtils.isNotBlank(item.getField())) {
                    Sort sortItem = new Sort(Sort.Direction.fromString(item.getOrder()),item.getField());
                    sort = sort == null ? sortItem : sort.and(sortItem);
                }
            }
        }
        return sort;
    }
    
}
