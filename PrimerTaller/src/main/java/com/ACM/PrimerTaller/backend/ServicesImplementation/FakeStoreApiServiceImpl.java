package com.ACM.PrimerTaller.backend.ServicesImplementation;

import com.ACM.PrimerTaller.backend.DTO.Auth.AuthRequestDTO;
import com.ACM.PrimerTaller.backend.DTO.Auth.AuthResponseDTO;
import com.ACM.PrimerTaller.backend.DTO.Product.Cart;
import com.ACM.PrimerTaller.backend.DTO.Product.Product;
import com.ACM.PrimerTaller.backend.Services.FakeStoreApiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FakeStoreApiServiceImpl implements FakeStoreApiService {
    private final RestTemplate restTemplate;
    private final String baseUrl ="https://fakestoreapi.com";

    public FakeStoreApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<AuthResponseDTO> authenticate(AuthRequestDTO authRequestDTO) {
        String url = baseUrl + "/auth/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequestDTO> entity = new HttpEntity<>(authRequestDTO, headers);

        try{
            AuthResponseDTO responseDTO = restTemplate.postForObject(url, entity, AuthResponseDTO.class);
            return Optional.ofNullable(responseDTO);
        } catch (HttpClientErrorException.Unauthorized e) {
            System.err.println("Autenticación fallida "+e.getMessage());
            return Optional.empty();
        } catch (Exception e){
            System.err.println("Error al autenticar usuario "+e.getMessage());
            return Optional.empty();
        }
    }

    public List<Product> getAllProducts() {
        String url = baseUrl + "/products";
        try{
            Product[] productsArray = restTemplate.getForObject(url, Product[].class);
            return Arrays.asList(Objects.requireNonNull(productsArray));
        } catch (Exception e){
            System.err.println("Error al obtener los productos "+e.getMessage());
            return List.of();
        }
    }
    public List<Cart> getCartsByUserId(Integer userId){
        String url = baseUrl + "/carts";
        try{
            Cart[] cartsArray = restTemplate.getForObject(url,Cart[].class);
            return Arrays.asList(Objects.requireNonNull(cartsArray));
        } catch (Exception e){
            System.err.println("Error al obtener los carritos "+e.getMessage());
            return List.of();
        }
    }
}
