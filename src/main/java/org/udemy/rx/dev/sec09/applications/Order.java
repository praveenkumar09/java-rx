package org.udemy.rx.dev.sec09.applications;

public record Order(Integer userId,
                    String productName,
                    String price) {
}
