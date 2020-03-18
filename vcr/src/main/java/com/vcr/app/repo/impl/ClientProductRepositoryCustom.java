package com.vcr.app.repo.impl;

import java.util.List;

import com.vcr.app.document.Clients;
import com.vcr.app.dto.ClientProduct;

public interface ClientProductRepositoryCustom {

	public List<ClientProduct> find_the_sum_of_amount_each_product_sold_to_the_client();

	public List<Clients> find_all_client_which_takes_more_than_more_product();

}