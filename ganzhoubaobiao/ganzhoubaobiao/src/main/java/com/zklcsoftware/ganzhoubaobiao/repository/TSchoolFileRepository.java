package com.zklcsoftware.ganzhoubaobiao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.zklcsoftware.basic.repository.BaseDao;
import com.zklcsoftware.ganzhoubaobiao.domain.TSchoolFile;

/**
 * Service Interface:TSchoolFile
 * @author administrator
 * @date 2019-3-18
 */
@Repository
public interface TSchoolFileRepository extends BaseDao<TSchoolFile,Integer>, TSchoolFileRepositoryExt {
	List<TSchoolFile> findByProjectSchoolIdAndType(Integer tProjectSchoolId,Integer Type);
}
