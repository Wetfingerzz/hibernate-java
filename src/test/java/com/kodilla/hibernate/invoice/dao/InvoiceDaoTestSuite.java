package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {
    @Autowired
    InvoiceDao invoiceDao;

    @Test
    public void testInvoiceDaoSave() {
        //Given
        Product p1 = new Product("Product No.1");
        Product p2 = new Product("Product No.2");
        Product p3 = new Product("Product No.3");

        Item i1 = new Item(p1, new BigDecimal(150), 2, new BigDecimal(250));
        Item i2 = new Item(p2, new BigDecimal(250), 4, new BigDecimal(350));
        Item i3 = new Item(p3, new BigDecimal(350), 6, new BigDecimal(450));

        Invoice invoice = new Invoice("01/06/2020");
        invoice.getItems().add(i1);
        invoice.getItems().add(i2);
        invoice.getItems().add(i3);
        i1.setInvoice(invoice);
        i2.setInvoice(invoice);
        i3.setInvoice(invoice);
        //When
        invoiceDao.save(invoice);
        int invoiceId = invoice.getId();
        //Then
        Assert.assertNotEquals(0,invoiceId);
        //CleanUp
        invoiceDao.deleteById(invoiceId);
    }
}
