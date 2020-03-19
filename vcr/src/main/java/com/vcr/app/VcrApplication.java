package com.vcr.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.vcr.app.document.Clients;
import com.vcr.app.document.Products;
import com.vcr.app.document.Vendors;
import com.vcr.app.exceptions.ResourceNotFoundException;
import com.vcr.app.helpers.ScannerUtility;
import com.vcr.app.helpers.TimeStampUtility;
import com.vcr.app.helpers.UniqueCode;
import com.vcr.app.repo.impl.ClientProductRepositoryCustom;
import com.vcr.app.repo.impl.ClientProductRepositoryImpl;
import com.vcr.app.repo.impl.ClientRepository;
import com.vcr.app.repo.impl.ProductRepository;
import com.vcr.app.repo.impl.VendorRepository;

@SpringBootApplication
public class VcrApplication {

	public static void main(String[] args) throws ResourceNotFoundException {
		ApplicationContext ac = SpringApplication.run(VcrApplication.class, args);
		ClientRepository clientRepository = ac.getBean(ClientRepository.class);
		ProductRepository productRepository = ac.getBean(ProductRepository.class);
		VendorRepository vendorRepository = ac.getBean(VendorRepository.class);
		ClientProductRepositoryCustom clientProductRepositoryCustom = ac.getBean(ClientProductRepositoryImpl.class);

		Scanner sc = ScannerUtility.getInstance();
		while (true) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("| 1. Add Products                                           |");
			System.out.println("| 2. Find All Product                                       |");
			System.out.println("| 3. Add Vendors                                            |");
			System.out.println("| 4. Find All Vendors                                       |");
			System.out.println("| 5. Add Clients                                            |");
			System.out.println("| 6. Find All Clients                                       |");
			System.out.println("| 7. Find all Client Which takes (2/3/4) Product            |");
			System.out.println("| 8. Find all the Client for Vendors                        |");
			System.out.println("| 9. Find all Count of Product, Vendors and Client we have  |");
			System.out.println("|10. Find the sum of amount each product Sold to the client |");
			System.out.println("|11. Find the Product By Vendor Name                        |");
			System.out.println("|12. Find the highest Amount Vendors-Product                |");
			System.out.println("|13. Find the Client which takes vendor Products            |");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Choose any operation you wanna perform");
			Integer option = sc.nextInt();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			switch (option) {
			case 1:
				System.out.println("How Many Product you wanno add");
				int noOfProd = sc.nextInt();
				for (int i = 0; i < noOfProd; i++) {
					Products products = new Products();
					products.setProductId(Integer.parseInt(UniqueCode.randomUUID(4, 0, '0')));
					System.out.println("Enter Product Name");
					products.setProductName(sc.next());
					System.out.println("Enter Product Description");
					products.setDescription(sc.next());
					System.out.println("Enter Product Price");
					products.setPricing(sc.nextDouble());
					products.setActive(false);
					products.setUpdateTimestamp(TimeStampUtility.currentMiliSecond());
					productRepository.save(products);
					System.out.println("Product Saved Successfuly");
				}
				break;
			case 2:
				System.out.println("-------------------------------------------------------------------");
				productRepository.findAll().forEach(System.out::println);
				System.out.println("-------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("How Many Vendors you wanna add");
				int noOfVendor = sc.nextInt();
				for (int i = 0; i < noOfVendor; i++) {
					Vendors vendor = new Vendors();
					vendor.setVendor_id(Integer.parseInt(UniqueCode.randomUUID(4, 0, '0')));
					System.out.println("Enter Vendor Name");
					vendor.setVendorName(sc.next());
					vendor.setActive(false);
					vendor.setUpdateTimestamp(TimeStampUtility.currentMiliSecond());

					System.out.println("-------------------------------------------------------------------");
					productRepository.findAll().forEach(System.out::println);
					System.out.println("-------------------------------------------------------------------");
					System.out.println("How many product wanna allocate to Vendors");
					int noOfP = sc.nextInt();
					List<Products> productsList = new ArrayList<Products>();
					for (int j = 0; j < noOfP; j++) {
						System.out.println("Enter Product id");
						Optional<Products> products = productRepository.findByProductId(sc.nextInt());
						if (products.isPresent())
							productsList.add(products.get());
						else
							throw new ResourceNotFoundException("Product Not found :" + sc.nextInt() + "this Id");
					}
					vendor.setProductList(productsList);
					vendorRepository.save(vendor);
					System.out.println("Vendor Saved Successfuly");
				}

				break;
			case 4:
				vendorRepository.findAll().forEach(System.out::println);
				break;
			case 5:
				System.out.println("How Many Client you wanna add");
				int noOfClient = sc.nextInt();
				for (int i = 0; i < noOfClient; i++) {
					Clients clients = new Clients();
					clients.setClientId(Integer.parseInt(UniqueCode.randomUUID(4, 0, '0')));
					System.out.println("Enter Client Name");
					clients.setName(sc.next());
					System.out.println("Enter Contact No");
					clients.setContact(sc.next());
					clients.setActive(false);
					clients.setUpdateTimestamp(1584437872294l);

					System.out.println("-------------------------------------------------------------------");
					productRepository.findAll().forEach(System.out::println);
					System.out.println("-------------------------------------------------------------------");
					System.out.print("How many product wanna buy : ");
					int noOfP = sc.nextInt();
					List<Products> productsList = new ArrayList<Products>();
					for (int j = 0; j < noOfP; j++) {
						System.out.print("Enter Product id : ");
						Optional<Products> products = productRepository.findByProductId(sc.nextInt());
						if (products.isPresent())
							productsList.add(products.get());
						else
							throw new ResourceNotFoundException("Product Not found :" + sc.nextInt() + "this Id");
					}
					clients.setProductList(productsList);
					clientRepository.save(clients);
					System.out.println("client Saved Successfuly");
				}

				break;
			case 6:
				clientRepository.findAll().forEach(System.out::println);
				break;
			case 7:
				clientProductRepositoryCustom.find_all_client_which_takes_more_than_more_product()
						.forEach(System.out::println);
				break;
			case 8:
				break;
			case 9:
				System.out.println("Product count is : " + productRepository.count());
				System.out.println("Vendor count is : " + vendorRepository.count());
				System.out.println("Client count is : " + clientRepository.count());
				break;
			case 10:
				clientProductRepositoryCustom.find_the_sum_of_amount_each_product_sold_to_the_client()
						.forEach(System.out::println);
				break;
			case 11:
				System.out.println("Enter Vendor Name to Find Product");
				String vendorName = sc.next();
				clientProductRepositoryCustom.find_the_product_by_vendor_name(vendorName).forEach(System.out::println);
				break;
			case 12:
				clientProductRepositoryCustom.find_the_highest_amount_vendors_product().forEach(System.out::println);
				break;
			case 13:
				System.out.println("Enter Vendor Name to Find Client");
				String vName = sc.next();
				clientProductRepositoryCustom.find_the_client_which_take_vendor_products(vName);
				break;
			default:
				System.exit(0);
				break;
			}
		}
	}
}
