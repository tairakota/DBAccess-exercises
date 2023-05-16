package org.example;

public class Main {
    public static void main(String[] args) {
        var userService = new ProductService();

        var userId = userService.findById(101);
        System.out.println(userId);

        var userName = userService.findByName("橋");
        userName.stream().forEach(System.out::println);

        var insert = new ProductRecord(106, "", 250);
        var userInsert = userService.insert(insert);
        System.out.println(userInsert);

        var update = new ProductRecord(104, "筆" , 500);
        var userUpdate = userService.update(update);
        System.out.println(userUpdate);

        var userDelete = userService.delete(106);
        System.out.println(userDelete);
    }
}