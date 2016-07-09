#include "auxiliary.h"
#include <string>
#include <iostream>
#include <iomanip>
#include <sstream>

using namespace std;

void SetMax(types& type)
{
	type.Max = type.Plus;
	if (type.Max < type.Minus) type.Max = type.Minus;
	if (type.Max < type.Multiply) type.Max = type.Multiply;
	if (type.Max < type.Divide) type.Max = type.Divide;
}

void Show(string sign, string sign_name, double value, double max_value)
{
	stringstream ss;
	string str="";

	//writing operation sign and its name
	str += sign;
	for (int i = 0; i < (FIRST_COLUMN - sign.length());++i)
		str += " ";
	str += sign_name;
	for (int i = 0; i < (SECOND_COLUMN - sign_name.length()); ++i)
		str += " ";

	//writing operations per second number
	ss << value;
	str += ss.str();
	for (int i = 0; i < (THIRD_COLUMN - ss.str().length()); ++i)
		str += " ";

	//visualisation result
	int N = int(((value / max_value)*FOURTH_COLUMN));
	N = (N < 0 ? 0 : N);
	N = (N > FOURTH_COLUMN ? FOURTH_COLUMN : (N == 0 ? 1 : N));
	for (int i = 0; i < N; ++i)
		str += "X";
	for (int i = 0; i < (FOURTH_COLUMN - N + 1); ++i)
		str += " ";

	//personalised percent computing
	double percent = (value / max_value) * 100;
	if (percent < 1 && percent >= 0.01)
		percent = double(int(percent * 100)) / 100; //like 0.02
	else
		percent = int(percent); //floor
	ss.str("");
	ss << percent;
	for (int i = 0; i < (FIFTH_COLUMN - ss.str().length()); ++i)
		str += " ";
	str += ss.str();
	str += "%\n";

	cout << str;
}