package pl.waw.great.shop.service;

import org.springframework.stereotype.Service;
import pl.waw.great.shop.exception.CartIsEmptyException;
import pl.waw.great.shop.exception.UserWithGivenNameNotExistsException;
import pl.waw.great.shop.model.Order;
import pl.waw.great.shop.model.OrderLineItem;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.model.User;
import pl.waw.great.shop.model.dto.OrderDto;
import pl.waw.great.shop.model.mapper.OrderMapper;
import pl.waw.great.shop.repository.Cart;
import pl.waw.great.shop.repository.OrderRepository;
import pl.waw.great.shop.repository.ProductRepository;
import pl.waw.great.shop.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;


    private final ProductRepository productRepository;

    private final Cart cart;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository, Cart cart) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
        this.cart = cart;
    }

    public OrderDto createOrder(String name) {

        List<OrderLineItem> orderItems = this.cart.getCartItems();

        if (orderItems.isEmpty()) {
            throw new CartIsEmptyException();
        }

        User user = this.userRepository.findUserByTitle(name)
                .orElseThrow(() -> new UserWithGivenNameNotExistsException(name));

        Order order = new Order(getOrderTotalAmount(orderItems),
                user,
                orderItems,
                LocalDateTime.now());

        OrderDto orderDto = orderMapper.orderToDto(this.orderRepository.create(order));
        this.updateProductsQuantity(orderItems);
        this.cart.clear();
        return orderDto;
    }

    private BigDecimal getOrderTotalAmount(List<OrderLineItem> orderItems) {
        return orderItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void updateProductsQuantity(List<OrderLineItem> itemList) {
        itemList.forEach(item -> {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
            this.productRepository.updateProduct(product);
        });
    }
}