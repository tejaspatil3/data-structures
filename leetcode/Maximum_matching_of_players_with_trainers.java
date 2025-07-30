import java.util.*;;

class Maximum_matching_of_players_with_trainers{
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
        System.out.println("Maximum_matching_of_players_with_trainers");
        int[] players = {4,7,9};
        int[] trainers = {8,2,5,8};

        System.out.println(matchPlayersAndTrainers(players,trainers));
    }
};