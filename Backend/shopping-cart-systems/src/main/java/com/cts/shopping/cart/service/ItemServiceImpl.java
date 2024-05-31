package com.cts.shopping.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.shopping.cart.model.CartDetails;
import com.cts.shopping.cart.model.ItemDetails;
import com.cts.shopping.cart.repository.CartRepo;
import com.cts.shopping.cart.repository.ItemRepo;
import com.cts.shopping.cart.response.UserInfo;

@Service
public class ItemServiceImpl implements ItemService {

	private Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private ClientService clientService;

	@Override
	public List<ItemDetails> getAllItems() {
		return itemRepo.findAll();
	}

	@Override
	public List<ItemDetails> getItemBySku(String sku) {
		System.out.println(itemRepo.findBySku(sku));
		return itemRepo.findBySku(sku);
	}

	@Override
	public CartDetails addItemToCart(String sku, int quantity, String username) {
		logger.info("Into Cart Service-1");
		List<ItemDetails> itemDetails = getItemBySku(sku);
		logger.info("Into Cart Service-2" + itemDetails);
		UserInfo userInfo = clientService.fetchUserInfo(username);
		long userId = userInfo.getId();

		CartDetails cartDetails = new CartDetails();
		List<CartDetails> existingCart = cartRepo.findBySkuAndUserid(sku, userId);
		System.out.println(existingCart.size() + " : Existing Cart Size");
		System.out.println(existingCart + " : Existing Cart");

		if (existingCart.size() > 0) {
			if (itemDetails.get(0).getQuantity() >= quantity) {
				logger.info("Into Cart Service-3A");
				cartDetails = new CartDetails(existingCart.get(0).getId(), userId, itemDetails.get(0).getSku(),
						quantity, itemDetails.get(0).getCost(), itemDetails.get(0).getImage(), "A",
						itemDetails.get(0).getDescription(), itemDetails.get(0).getMfr(),
						itemDetails.get(0).getVendor());
			} else {
				logger.info("Into Cart Service-3B");
				cartDetails = new CartDetails(existingCart.get(0).getId(), userId, itemDetails.get(0).getSku(),
						quantity, itemDetails.get(0).getCost(), itemDetails.get(0).getImage(), "C",
						itemDetails.get(0).getDescription(), itemDetails.get(0).getMfr(),
						itemDetails.get(0).getVendor());
			}
		} else {
			if (itemDetails.get(0).getQuantity() >= quantity) {
				logger.info("Into Cart Service-4A");
				cartDetails = new CartDetails(userId, itemDetails.get(0).getSku(), quantity,
						itemDetails.get(0).getCost(), itemDetails.get(0).getImage(), "A",
						itemDetails.get(0).getDescription(), itemDetails.get(0).getMfr(),
						itemDetails.get(0).getVendor());
			} else {
				logger.info("Into Cart Service-4B");
				cartDetails = new CartDetails(userId, itemDetails.get(0).getSku(), quantity,
						itemDetails.get(0).getCost(), itemDetails.get(0).getImage(), "C",
						itemDetails.get(0).getDescription(), itemDetails.get(0).getMfr(),
						itemDetails.get(0).getVendor());
			}
		}

		logger.info("Into Cart Service-5");
		logger.info(cartDetails.toString());
		cartRepo.save(cartDetails);

		logger.info("Into Cart Service-6");
		return cartDetails;
	}

	@Override
	public List<CartDetails> getUserCarts(String username) {
		// TODO Auto-generated method stub
		logger.info("Into User Cart Service-1");
		UserInfo userInfo = clientService.fetchUserInfo(username);
		long userId = userInfo.getId();
		List<CartDetails> cartDetails = cartRepo.findByUserid(userId);
		return cartDetails;
	}

	@Override
	public List<String> checkOutCarts(List<Long> ids, String username) {
		List<String> checkOutMsg = new ArrayList<>();
		try {
			for (long id : ids) {
				logger.info(id + ": will be deleted ");
				Optional<CartDetails> cartDetails = cartRepo.findById(id);
				logger.info("Cart Details: " + cartDetails.get().toString());
				if (cartDetails.get().getStatus().equals("A")) {
					logger.info("Cart Details Checkout- 1");
					ItemDetails itemDetails = getItemBySku(cartDetails.get().getSku()).get(0);
					logger.info("Cart Details Checkout- 2");
					ItemDetails itemDetails2 = new ItemDetails(itemDetails.getId(), itemDetails.getSku(),
							itemDetails.getDescription(), (itemDetails.getQuantity() - cartDetails.get().getQuantity()),
							itemDetails.getCost(), itemDetails.getMfr(), itemDetails.getVendor(),
							itemDetails.getImage());
					logger.info("Cart Details Checkout- 3");
					itemRepo.save(itemDetails2);
					logger.info("Cart Details Checkout- 4");
					cartRepo.deleteById(id);
					logger.info(id + ": Deleted");
					logger.info("Cart Details Checkout- 5");

					List<CartDetails> existingCartDetails = cartRepo.findBySku(cartDetails.get().getSku());
					System.out.println(existingCartDetails.size() + " : Existing Cart Size");
					System.out.println(existingCartDetails + " : Existing Cart");
					for (CartDetails existingCart : existingCartDetails) {
						System.out.println(existingCart + " Existing Cart After Delete");
						ItemDetails itemDetails3 = getItemBySku(cartDetails.get().getSku()).get(0);
						if (existingCart != null) {
							if (itemDetails3.getQuantity() >= existingCart.getQuantity()) {
								logger.info("Into Cart Service-3A");
								CartDetails updatingCartStatus = new CartDetails(existingCart.getId(),
										existingCart.getUserid(), existingCart.getSku(), existingCart.getQuantity(),
										existingCart.getCost(), existingCart.getImage(), "A",
										existingCart.getDescription(), existingCart.getMfr(), existingCart.getVendor());
								cartRepo.save(updatingCartStatus);
							} else {
								logger.info("Into Cart Service-3B");
								CartDetails updatingCartStatus = new CartDetails(existingCart.getId(),
										existingCart.getUserid(), existingCart.getSku(), existingCart.getQuantity(),
										existingCart.getCost(), existingCart.getImage(), "C",
										existingCart.getDescription(), existingCart.getMfr(), existingCart.getVendor());
								cartRepo.save(updatingCartStatus);
							}

						}
					}
					checkOutMsg.add("SKU: " + cartDetails.get().getSku() + " Check Out Successfully");
				} else {
					logger.info("Cart Details Checkout- 6");
					checkOutMsg.add("SKU: " + cartDetails.get().getSku() + " is Out Of Stock");
				}
			}
		} catch (Exception e) {
			logger.info("Exception in CheckOutCart Method: " + e);
			return checkOutMsg;
		}
		return checkOutMsg;
	}

	@Override
	public List<String> deleteFromCart(List<Long> ids, String username) {
		List<String> checkOutMsg = new ArrayList<>();
		try {
			for (long id : ids) {
				logger.info(id + ": will be deleted ");
				Optional<CartDetails> cartDetails = cartRepo.findById(id);
				logger.info("Cart Details: " + cartDetails.get().toString());
				cartRepo.deleteById(id);
				checkOutMsg.add("SKU: " + cartDetails.get().getSku() + " is Deleted Out Successfully");
			}
		} catch (Exception e) {
			logger.info("Exception in CheckOutCart Method: " + e);
			return checkOutMsg;
		}
		return checkOutMsg;
	}

}
