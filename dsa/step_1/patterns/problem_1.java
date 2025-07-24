import java.util.*;

public class problem_1 {

    /// not corrct use two pointers 
    /// players are not marked as 
    // public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
    //     int playlength = players.length;
    //     int trainerlength = trainers.length;
    //     Arrays.sort(trainers);
    //     Arrays.sort(players);
    //     int count=0;
    //     if( trainerlength == 1 && players[0] <=trainers[0]) {
    //         count = 1;
    //         return count;
    //     }
    //     else{
    //         for( int i = 0 ;i<playlength;i++){
    //             for(int j = i;j<trainerlength;j++){

    //                 if(players[i] <= trainers[j]){
    //                     count++;
    //                     break;
    //                 }
    //                 if(count >= trainerlength){
    //                     return count;
    //                 }
    //             }
    //         }
    //     }
     
    //     System.out.println(count);
    //     return count;
           
    // }

    public static int matchPlayersAndTrainers(int[] players, int[] trainers){
        int playlength = players.length;
        int trainerlength = trainers.length;
        int count = 0;

        Arrays.sort(players);
        Arrays.sort(trainers);
        int i = 0;
        int j = 0;
        while(i < playlength){
            if(j == trainerlength){
                break;
            }
            if (players[i] <= trainers[j]) {
                count++;
                i++;
                j++;
            } else {
                j++; 
            }
        }

        

        return count;

    }
    
    public static void main(String[] args) {
        int[] players = {1,2,3};
        int[] trainers = {3,2};

        matchPlayersAndTrainers(players,trainers);
    }
    // public int matchPlayersAndTrainers(int[] players, int[] trainers) {
    //     int playlength = players.length;
    //     int trainerlength = trainers.length;
    //     int count=0;
    //     if()
    //     for( int i = 0 ;i<playlength;i++){
    //         for(int j = i;j<trainerlength;j++){

    //             if(players[i] <= trainers[j]){
    //                 count++;
    //                 break;
    //             }
                

    //         }
            
    //     }
    //     System.out.print(count+"    Go");
        
    // }
}

