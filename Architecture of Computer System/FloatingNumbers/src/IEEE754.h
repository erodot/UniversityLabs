#pragma once

#define EXPONENT_LENGTH 11	//bits in exponent, max 64
#define MANTISSA_LENGTH 52	//bits in mantissa

using namespace std;

//converts number with floating point to IEEE754 standart view
string ConvertFloatingToBinaryIEEE754(string);

//converts decimal mantissa to common decimal view
string ClassicalizeDecMantissa(string, string);

//converts mantissa from decimal to binary (still common view)
string ConvertFloatingNumberToBin(string);

//transfers binary mantissa into normalized view
string NormalizeBinMantissa(string, long long&);

//removes implicit bit
string ShiftMantissa(string);

//defines offset binary exponent in dec
string Int64ToBin(long long);

//gets collection of unusual input data
string getUnusualData();