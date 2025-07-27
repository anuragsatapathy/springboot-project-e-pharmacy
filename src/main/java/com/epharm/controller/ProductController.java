package com.epharm.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.epharm.entity.Products;
import com.epharm.service.ProductList;
import com.epharm.service.ProductsService;
import com.epharm.service.UserCartsService;
import com.epharm.service.UserService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCartsService userCartsService;

	@GetMapping("/productlist")
	public String product(Model m,Principal principal) {
		String email=principal.getName();
		Integer userid=userService.getUserId(email);
		List<Products> product = productsService.ListProduct();
		List<ProductList> list = new ArrayList<>();
		Integer carts = 0;
		for(Products k : product) {
			Integer quantity=userCartsService.getQuantity(userid, k.getId());
			ProductList productlist = new ProductList();
			productlist.setId(k.getId());
			productlist.setName(k.getName());
			productlist.setPrice(k.getPrice());
			productlist.setProductquantity(quantity);
			productlist.setImagepath(k.getImagepath());
			carts=carts + quantity;
			list.add(productlist);
		}
		m.addAttribute("Products", list);
		m.addAttribute("cartsquantity", carts);
		return "productlist";
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/createproduct")
	public String createProduct(Model m) {
		
		m.addAttribute("products", new Products());
		return "createproduct";
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createproduct")
	public String createProductpost(Products product, @RequestParam("image") MultipartFile imagefile) throws IOException {
		String filename = UUID.randomUUID() + "_" + imagefile.getOriginalFilename();
		Path uploadpath = Paths.get("uploads/products/");
		Files.createDirectories(uploadpath);
		Path filepath = uploadpath.resolve(filename);
		Files.copy(imagefile.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
		product.setImagepath("/uploads/products/"+filename);
		productsService.createProduct(product);
		
		return "redirect:/productlist";
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/updateproducts")
	public String updateProduct(Products product, @RequestParam("image") MultipartFile imagefile) throws IOException {
		String filename = UUID.randomUUID() + "_" + imagefile.getOriginalFilename();
		Path uploadpath = Paths.get("uploads/products/");
		Files.createDirectories(uploadpath);
		Path filepath = uploadpath.resolve(filename);
		Files.copy(imagefile.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
		product.setImagepath("/uploads/products/"+filename);
		productsService.updateProduct(product);
		return "redirect:/productlist";
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteproducts/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productsService.deleteProduct(id);
		return "redirect:/productlist";
		
	}
	
	@GetMapping("/uploads/products/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveImage(@PathVariable String filename) throws IOException{
		Path filepath = Paths.get("uploads/products/").resolve(filename);
		Resource resource = new UrlResource(filepath.toUri());
		if(resource.exists()) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(filepath)).body(resource);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
