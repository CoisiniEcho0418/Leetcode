import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String,Integer> hashmap = new HashMap<>();
        for(String str:strs){
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String tempStr = String.valueOf(temp);
            if(hashmap.containsKey(tempStr)){
                List<String> list = result.get(hashmap.get(tempStr));
                list.add(str);
            }else {
                hashmap.put(tempStr,hashmap.size());
                List<String > newList = new ArrayList<>();
                newList.add(str);
                result.add(newList);
            }
        }

        return result;
    }
}
