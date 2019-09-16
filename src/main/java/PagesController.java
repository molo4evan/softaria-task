import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class PagesController {
    private Hashtable<String, String> yesterdayPages = new Hashtable<>();
    private Hashtable<String, String> todayPages = new Hashtable<>();

    public void changeDay(){
        yesterdayPages.clear();
        yesterdayPages.putAll(todayPages);
    }

    public boolean addPage(String URL, String content){
        return (todayPages.putIfAbsent(URL, content) == null);
    }

    public boolean removePage(String URL){
        return (todayPages.remove(URL) != null);
    }

    public boolean editPage(String URL, String newContent){
        return (todayPages.replace(URL, newContent) != null);
    }

    public List<String> getDeletedURLs(){
        return yesterdayPages.keySet().parallelStream().
                filter(x -> !todayPages.containsKey(x)).
                collect(Collectors.toList());
    }

    public List<String> getAddedURLs(){
        return todayPages.keySet().parallelStream().
                filter(x -> !yesterdayPages.containsKey(x)).
                collect(Collectors.toList());
    }

    public List<String> getEditedURLs(){
        return  todayPages.keySet().parallelStream().
                filter(yesterdayPages::containsKey).
                filter(x ->
                        !todayPages.get(x).equals(yesterdayPages.get(x))
                ).collect(Collectors.toList());
    }
}
