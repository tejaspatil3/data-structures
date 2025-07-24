
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Remove_Sub_Folders_from_the_Filesystem{

    public static List<String> removeSubFolders(String[] folder) {
        Arrays.sort(folder);

        List<String> mark = new ArrayList<>();

        for (String currentFolder : folder) {
            if (mark.isEmpty() || !currentFolder.startsWith(mark.get(mark.size() - 1) + "/")) {
                mark.add(currentFolder);
            }
        }

        return mark;
    }
    public static void main(String[] args) {
        System.out.println("hello world");
        String[] mark = {"/a/b","/a/b/c","/c/b","/a"};

        System.out.print(removeSubFolders(mark));
        
    }
};