package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public CartLine get(int id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartLine) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(CartLine cartLine) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		// TODO Auto-generated method stub

		String query = "FROM CartLine WHERE cartId = :cartId";

		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";

		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.setParameter("available", true).getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
					.setParameter("productId", productId).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	// related to the cart
	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
