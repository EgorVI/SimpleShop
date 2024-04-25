package com.egor.venediktov.little.demo.simple_shop;

import com.egor.venediktov.little.demo.simple_shop.dao.InvoiceItemRepository;
import com.egor.venediktov.little.demo.simple_shop.dao.InvoiceRepository;
import com.egor.venediktov.little.demo.simple_shop.model.Invoice;
import com.egor.venediktov.little.demo.simple_shop.port.input.rest.dto.InvoiceDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
class SimpleShopBaseShopControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void positive_save_and_getInvoiceById() {
        //given
        Invoice invoice = TestUtils.getInvoiceWithItems();
        invoiceRepository.save(invoice);
        invoice.getItems().forEach(invoiceItem -> invoiceItemRepository.save(invoiceItem));
        InvoiceDTO expectedInvoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);
        expectedInvoiceDTO.setFinalMinimalUnitsPrice(4);

        //when
        InvoiceDTO actualdInvoiceDto = restTemplate.getForObject(
                "http://localhost:" + port + "/invoice/" + invoice.getId(),
                InvoiceDTO.class);

        //then
        assertEquals(expectedInvoiceDTO, actualdInvoiceDto);
    }

    @Test
    void negative_getInvoiceById_wrong_id() {

        ResponseEntity<InvoiceDTO> response = restTemplate.exchange(
                "http://localhost:" + port + "/invoice/" + new Random().nextInt(),
                HttpMethod.GET,
                null,
                InvoiceDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
