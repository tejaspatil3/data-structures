#include <iostream>
#include <vector>
using namespace std;

int maxActions(int N, vector<int>& A) {
    int count = 0;
    vector<bool> swapped(N - 1, false); // Track swapped indices
    
    for (int j = 0; j < N - 1; j++) {
        if (A[j] == 3 && A[j + 1] == 4 && !swapped[j]) {
            count++;
            swap(A[j], A[j + 1]); // Perform the swap
            swapped[j] = true; // Mark index as swapped
        }
    }
    return count;
}

int main() {
    int N;
    cin >> N;
    vector<int> A(N);
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }
    cout << maxActions(N, A) << endl;
    return 0;
}