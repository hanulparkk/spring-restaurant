package me.spring.restaurant.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RegionTests {

    @Test
    public void creation() {
        Region region = Region.builder().name("Seoul").build();

        assertThat(region.getName(), is("Seoul"));
    }
}
