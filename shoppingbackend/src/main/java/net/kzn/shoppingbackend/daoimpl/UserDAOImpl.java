package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory SessionFactory;

	@Override
	public boolean addUser(User user) {
		try {
			SessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addAddress(Address address) {
		try {
			SessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		String selectQuery = "FROM User WHERE email=:email";
		try {
			return SessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Address getBillingAddress(User user) {
		// TODO Auto-generated method stub
		String selectQuery = "FROM ADDRESS WHERE  user=:user AND billing = :billing";

		try {
			return SessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("billing", true).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Address> listShippingAddress(User user) {
		// TODO Auto-generated method stub

		String selectQuery = "FROM ADDRESS WHERE  user=:user AND shipping = :shipping";

		try {
			return SessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("shipping", true).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
