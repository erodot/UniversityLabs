#pragma once
#include <iostream>

using namespace std;

//constants that defining program working properties

//visual settings
#define FIRST_COLUMN 3
#define SECOND_COLUMN 8
#define THIRD_COLUMN 13
#define FOURTH_COLUMN 48
#define FIFTH_COLUMN 4

//number of operations
#define CPU_CLOCK_FREQUENCY 240000000
#define FIRST_DIVIDER 40
#define SECOND_DIVIDER 10
#define REAL_DIVIDER 10

//max random number
#define MULTIPLIER INT_MAX

struct types //container for values calculated by testing functions
{
	double Plus;
	double Minus;
	double Multiply;
	double Divide;
	double Max;
};

//obtain max of type.Plus, type.Minus, type.Multiply & type.Divide
void SetMax(types& type);

//output
void Show(string sign, string sign_name, double value, double max_value);