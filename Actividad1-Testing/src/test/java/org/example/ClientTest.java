package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ClientTest {
    private Address address = new Address("street A");
    private Address secondAddress = new Address("street B");
    private Client client;
    @BeforeEach
    void setUp()
    {
        client = new Client();
    }
    @Test
    void afterCreationShouldHaveNoAddress()
    {

    }
    @Test
    void shouldAllowToAddAddress()
    {
        client.addAddress(address);

    }
    @Test
    void shouldAllowToAddManyAddresses()
    {
        client.addAddress(address);
        client.addAddress(secondAddress);
    }
}