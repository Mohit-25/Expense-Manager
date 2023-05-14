package com.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.Entity.CategoryEntity;
import com.Entity.SubCategoryEntity;
import com.Entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
		public List<SubCategoryEntity> searchCriteria(Integer id) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SubCategoryEntity> query = builder.createQuery(SubCategoryEntity.class);

		Root<UserEntity> userRoot = query.from(UserEntity.class);
		Join<UserEntity, CategoryEntity> categoryJoin = userRoot.join("category");
		Join<CategoryEntity, SubCategoryEntity> subcategoryJoin = categoryJoin.join("subCategory");
		query.multiselect(userRoot, categoryJoin, subcategoryJoin);
		TypedQuery<SubCategoryEntity> typedQuery = entityManager.createQuery(query);
		List<SubCategoryEntity> results = typedQuery.getResultList();

		return results.subList(2, 2);

	}
}
