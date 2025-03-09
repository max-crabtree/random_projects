#include <iostream>
#include <fstream>
using namespace std;

int main(){
  ofstream KevFile("kevinmoment.txt");
  for(int x = 0; x < 60000; x++){
    KevFile << "Kevin Moment Kevin Moment Kevin Moment Kevin Moment Kevin Moment Kevin Moment Kevin Moment Kevin Moment Kevin Moment Kevin Moment\n";
  }
  KevFile.close();
}