#include <exception>
#include <iostream>
#include <string>
#include "IEEE754.h"

using namespace std;

int main()
{
	try
	{
		cout << getUnusualData() << "\n";

		string inputStr;

		cout << "Enter a normalized floating number: ";
		cin >> inputStr;

		cout << ConvertFloatingToBinaryIEEE754(inputStr) << endl;
	}
	catch(exception& e)
	{
		cout << "Critical error. Program crashed. " << e.what() << '\n';
	}
	return 0;
}
