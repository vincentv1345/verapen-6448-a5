package ucf.assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Vincent Verapen
 */
public class SearchClass {
    public List<Item> searchInventory(String search, List<Item> inventoryList){
        List<String> wordsForSearch = Arrays.asList(search.trim().split(" "));
        return inventoryList.stream().filter(input -> {
            return wordsForSearch.stream().allMatch(word ->
                    input.getName().toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
}
