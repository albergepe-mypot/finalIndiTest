package com.inditex.hiring.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import com.inditex.hiring.controller.dto.OfferByPartNumber;
import com.inditex.hiring.model.Producto;
import com.inditex.hiring.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * You can change this controller but please do not change ends points signatures & payloads.
 */
@RestController
public class OfferController {

  @Autowired
  private IProductoService iProductoService;


  @RequestMapping(value = "/offer", method = RequestMethod.POST, consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto createNewOffer(@RequestBody @Valid Producto product) {

    return iProductoService.saveProducto(product);
  }

  @RequestMapping(value = "/offer", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllOffers() {

    iProductoService.deleteAllProductos();

  }

  @RequestMapping(value = "/offer/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteOfferById(@PathVariable Long id) {

    iProductoService.deleteProducto(id);

  }

  @RequestMapping(value = "/offer", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<Producto> getAllOffers() {

    return iProductoService.getProductos();
  }


  @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public Producto getOfferById(@PathVariable Long id) {

    return iProductoService.getProductoById(id);
   }


  @RequestMapping(value = "brand/{brandId}/partnumber/{partnumber}/offer", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<OfferByPartNumber> getOfferByPartNumber(@PathVariable Integer brandId, @PathVariable String partnumber) {
    List<Producto> listProd = iProductoService.searchByPartNum(brandId,partnumber);

    return mappingByPartNumber(listProd);

  }

  private List<OfferByPartNumber> mappingByPartNumber(List<Producto> listProd)
  {
    List<OfferByPartNumber> listProdOBPN = new ArrayList<>();

    int cuantos=listProd.size();

    System.out.println("*********RESULTADOS*******************");
    System.out.println("****************************");
    for(int i=0;i<cuantos;i++)
    {
      OfferByPartNumber obpn = new OfferByPartNumber();
      obpn.setEndDate(listProd.get(i).getEnd_date());
      obpn.setStartDate(listProd.get(i).getStart_date());
      obpn.setPrice(listProd.get(i).getPrice());
      obpn.setCurrencyIso(listProd.get(i).getCurrency());


      System.out.println("desde " + obpn.getStartDate() + " hasta " + obpn.getEndDate() + " precio: " + obpn.getPrice() + " " + obpn.getCurrencyIso());

      listProdOBPN.add(obpn);
    }
    System.out.println("****************************");
    System.out.println("****************************");

    return listProdOBPN;
  }
}
