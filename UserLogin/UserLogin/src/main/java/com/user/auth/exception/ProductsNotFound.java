package com.user.auth.exception;

public class ProductsNotFound extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductsNotFound(String noProductsAvailable) {
        super(noProductsAvailable);
    }
}
