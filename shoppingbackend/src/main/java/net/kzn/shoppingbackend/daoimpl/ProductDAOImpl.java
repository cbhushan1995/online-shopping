package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Product;



@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Product get(int productId) {
		try{
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * LIST
	 */
	@Override
	public List<Product> list() {
		return sessionFactory
				.getCurrentSession()
				.createQuery("FROM Product",Product.class)
				.getResultList();
	}

	/*
	 * INSERT
	 */
	@Override
	public boolean add(Product product) {
		// TODO Auto-generated method stub
		try{
		sessionFactory.getCurrentSession().persist(product);return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * UPDATE
	 * (non-Javadoc)
	 * @see net.kzn.shoppingbackend.dao.ProductDAO#update(net.kzn.shoppingbackend.dto.Product)
	 */
	@Override
	public boolean update(Product product) {
		try{
			sessionFactory.getCurrentSession().update(product);return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * DELETE
	 * (non-Javadoc)
	 * @see net.kzn.shoppingbackend.dao.ProductDAO#delete(net.kzn.shoppingbackend.dto.Product)
	 */
	@Override
	public boolean delete(Product product) {
		// 
		try{
		product.setActive(false);
		return this.update(product);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		// TODO Auto-generated method stub
		String selectActiveProducts = "FROM Product WHERE active=:active";
		
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveProducts,Product.class)
				.setParameter("active", true)
				.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
String selectActiveProductsByCategor = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveProductsByCategor,Product.class)
				.setParameter("active", true)
				.setParameter("categoryId",categoryId)
				.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
				.createQuery("FROM Product WHERE active = :active  ORDER BY id",Product.class)
				.setParameter("active", true)
				.setFirstResult(0)
				.setMaxResults(count)
				.getResultList();

	}

}
