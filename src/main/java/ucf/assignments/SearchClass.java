package ucf.assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchClass {
    public List<Item> searchInventory(String search, List<Item> inventoryList){
        List<String> wordsForSearch = Arrays.asList(search.trim().split(" "));
        return inventoryList.stream().filter(input -> {
            return wordsForSearch.stream().allMatch(word ->
                    input.getName().toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
}
