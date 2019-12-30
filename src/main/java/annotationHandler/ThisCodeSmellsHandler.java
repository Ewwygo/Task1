package annotationHandler;

import annotations.ProdCode;
import annotations.ThisCodeSmells;
import annotations.ThisCodeSmellsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


public class ThisCodeSmellsHandler {

    private static HashMap<String, ArrayList<String>> smellsMap = new HashMap<>();

    @ProdCode
    public void run(Class clazz){

        if (clazz.isAnnotationPresent(ThisCodeSmells.class)){
            ThisCodeSmells annotation = (ThisCodeSmells) clazz.getAnnotation(ThisCodeSmells.class);
            ArrayList<String> reviewers = new ArrayList<>();
            reviewers.add(annotation.reviewer());
            smellsMap.put(clazz.toString(),reviewers);
        } else if (clazz.isAnnotationPresent(ThisCodeSmellsList.class)){
            ArrayList<String> reviewers = new ArrayList<>();
            ThisCodeSmellsList annotationList = (ThisCodeSmellsList) clazz.getAnnotation(ThisCodeSmellsList.class);
            for (ThisCodeSmells annotation: annotationList.value()
                 ) {
                reviewers.add(annotation.reviewer());
            }
            smellsMap.put(clazz.toString(),reviewers);
        }

        for (Method method: clazz.getDeclaredMethods()
             ) {
            if (method.isAnnotationPresent(ThisCodeSmells.class)){
                ArrayList<String> reviewers = new ArrayList<>();
                reviewers.add(method.getAnnotation(ThisCodeSmells.class).reviewer());
                smellsMap.put(method.toString(),reviewers);
            } else if(method.isAnnotationPresent(ThisCodeSmellsList.class)){
                ArrayList<String> reviewers = new ArrayList<>();
                for (ThisCodeSmells annotation: method.getAnnotation(ThisCodeSmellsList.class).value()
                     ) {
                    reviewers.add(annotation.reviewer());
                }
                smellsMap.put(method.toString(),reviewers);
            }
        }

        for (Field field: clazz.getDeclaredFields()
             ) {
            if (field.isAnnotationPresent(ThisCodeSmells.class)){
                ArrayList<String> reviewers = new ArrayList<>();
                reviewers.add(field.getAnnotation(ThisCodeSmells.class).reviewer());
                smellsMap.put(field.toString(),reviewers);
            } else if (field.isAnnotationPresent(ThisCodeSmellsList.class)){
                ArrayList<String> reviewers = new ArrayList<>();
                for (ThisCodeSmells annotation: field.getAnnotation(ThisCodeSmellsList.class).value()
                     ) {
                    reviewers.add(annotation.reviewer());
                }
                smellsMap.put(field.toString(),reviewers);
            }
        }
        mapToString();
    }
    
    public void mapToString(){
        Set<Map.Entry<String,ArrayList<String>>> entries = smellsMap.entrySet();

        Comparator<Map.Entry<String, ArrayList<String>>> sizeComparator = new Comparator<Map.Entry<String, ArrayList<String>>>() {
            @Override
            public int compare(Map.Entry<String, ArrayList<String>> stringArrayListEntry, Map.Entry<String, ArrayList<String>> t1) {
                Integer i1 = stringArrayListEntry.getValue().size();
                Integer i2 = t1.getValue().size();
                return i2.compareTo(i1);
            }
        };

        List<Map.Entry<String,ArrayList<String>>> listOfEntries = new ArrayList<>(entries);
        Collections.sort(listOfEntries,sizeComparator);
        LinkedHashMap<String,ArrayList<String>> sortedBySize = new LinkedHashMap<>(listOfEntries.size());

        for (Map.Entry<String,ArrayList<String>> entry: listOfEntries){
            sortedBySize.put(entry.getKey(),entry.getValue());
        }

        Set<Map.Entry<String,ArrayList<String>>> entrySetSortedBySize = sortedBySize.entrySet();

        System.out.println();
        for (Map.Entry<String,ArrayList<String>> mapping : entrySetSortedBySize){
            System.out.println(mapping.getKey() + " - reviewers said this code smells: " + mapping.getValue());
        }
    }
}
