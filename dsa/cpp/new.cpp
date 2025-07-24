#include <iostream>
#include <vector>

using namespace std;
int maxActions(int n, vector<int>& arr) {
    int actions = 0;

    // Traverse the array and count occurrences of (3,4) pairs
    for (int i = 0; i < n; i++) {
        if (arr[i] == 3 && arr[i + 1] == 4) {
            swap(arr[i], arr[i+1]);
            actions++;
            i=0;
        }
            
    }

    return actions;
}

int main() {
    int n=4;
    // cin >> n;  // Input number of elements in the array
    vector<int> arr = {3,3,4,4};

    // // Input array elements
    // for (int i = 0; i < n; i++) {
    //     cin >> arr[i];
    // }

    // Get the result
    int result = maxActions(n, arr);
    cout << result << endl;  // Output the maximum number of actions

    return 0;
}