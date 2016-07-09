#include <iostream>
#include <bitset>
#include <math.h>
#include <sstream>
#include "IEEE754.h"
#include "long arithmetics library\longInt.h"
#include "long arithmetics library\longIntNonMembers.h"

using namespace std;
using namespace LongIntInVector;

string ConvertFloatingToBinaryIEEE754(string _str)
{
	string _bits = "";

	#pragma region checking unusual input data

	//null value
	if (_str == "null" || _str=="NULL" || _str[0] == '0' || (_str[0] == '+'&&_str[1] == '0'))
	{
		for (long long i = 0; i < EXPONENT_LENGTH + MANTISSA_LENGTH + 1; ++i)
			_bits += "0";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}

	//minus null
	if (_str[0] == '-'&&_str[1] == '0')
	{
		_bits += "1";
		for (long long i = 0; i < EXPONENT_LENGTH + MANTISSA_LENGTH; ++i)
			_bits += "0";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}

	//infinity
	if (_str == "inf" || _str == "+inf" || _str == "INF" || _str == "+INF")
	{
		_bits += "0";
		for (long long i = 0; i < EXPONENT_LENGTH; ++i)
			_bits += "1";
		for (long long i = 0; i < MANTISSA_LENGTH; ++i)
			_bits += "0";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;	
	}

	//minus ifinity
	if (_str == "-inf" || _str == "-INF")
	{
		_bits += "1";
		for (long long i = 0; i < EXPONENT_LENGTH; ++i)
			_bits += "1";
		for (long long i = 0; i < MANTISSA_LENGTH; ++i)
			_bits += "0";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}

	//absolute max plus number
	if (_str == "MAX_PLUS")
	{
		_bits += "0";
		for (long long i = 0; i < EXPONENT_LENGTH-1; ++i)
			_bits += "1";
		_bits += "0";
		for (long long i = 0; i < MANTISSA_LENGTH; ++i)
			_bits += "1";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}

	//absolute min plus number
	if (_str == "MIN_PLUS")
	{
		_bits += "0";
		for (long long i = 0; i < EXPONENT_LENGTH - 1; ++i)
			_bits += "0";
		_bits += "1";
		for (long long i = 0; i < MANTISSA_LENGTH; ++i)
			_bits += "0";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}

	//absolute min minus number
	if (_str == "MIN_MINUS")
	{
		_bits += "1";
		for (long long i = 0; i < EXPONENT_LENGTH - 1; ++i)
			_bits += "0";
		_bits += "1";
		for (long long i = 0; i < MANTISSA_LENGTH; ++i)
			_bits += "0";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}

	//absolute max minus number
	if (_str == "MAX_MINUS")
	{
		_bits += "1";
		for (long long i = 0; i < EXPONENT_LENGTH - 1; ++i)
			_bits += "1";
		_bits += "0";
		for (long long i = 0; i < MANTISSA_LENGTH; ++i)
			_bits += "1";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}

	//Not a Number
	if (_str == "NaN")
	{
		for (long long i = 0; i < EXPONENT_LENGTH + MANTISSA_LENGTH + 1; ++i)
			_bits += "1";
		_bits.insert(EXPONENT_LENGTH + 1, " ");
		_bits.insert(1, " ");
		return _bits;
	}
	#pragma endregion

	#pragma region defining mantissa sign
	if (_str[0] == '-')
		_bits += "1";
	else if (_str[0] == '+')
		_bits += "0";
	else
	{
		_str.insert(0, "+");
		_bits += "0";
	}
	#pragma endregion

	size_t e_position = _str.find_first_of("Ee");

	#pragma region reading input
	//reading exponent from console
	string exponent_dec = _str.substr(e_position + 1, _str.length() - e_position - 1);

	//reading mantissa from console
	string mantissa_dec = _str.substr(1, e_position - 1);
	#pragma endregion

	#pragma region defining mantissa
	//converting decimal mantissa to common decimal view
	string classical_mantissa_dec = ClassicalizeDecMantissa(mantissa_dec, exponent_dec);

	//converting mantissa from decimal to binary (still common view)
	string classical_mantissa_bin = ConvertFloatingNumberToBin(classical_mantissa_dec);

	//transferring binary mantissa into normalized view
	long long exponent;
	string normalized_mantissa_bin = NormalizeBinMantissa(classical_mantissa_bin,exponent);

	//removing implicit bit
	string offset_normalized_mantissa_bin = ShiftMantissa(normalized_mantissa_bin);
	#pragma endregion

	#pragma region defining exponent
	//defining offset binary exponent in dec
	long long offset_exponent_bin_in_dec = exponent + pow(2, EXPONENT_LENGTH - 1) - 1;

	//defining offset binary exponent in dec
	string offset_exponent_bin = Int64ToBin(offset_exponent_bin_in_dec);
	#pragma endregion

	#pragma region writing results
	_bits += " ";
	_bits += offset_exponent_bin;
	_bits += " ";
	_bits += offset_normalized_mantissa_bin;
	#pragma endregion

	return _bits;
}

string ClassicalizeDecMantissa(string _mantissa, string _shift)
{
	string mantissa = _mantissa;
	long long shift = stoll(_shift);
	int point_position;
	long long shifted;
	if (shift > 0)
	{
		shifted = 0;
		while (shifted < shift && mantissa[0] == '0')
		{
			mantissa.erase(0, 2);
			mantissa.insert(1, ".");
			shifted++;
		}

		while (shifted < shift && mantissa[mantissa.length() - 1] != '.')
		{
			point_position = mantissa.find_first_of(".");
			mantissa.erase(point_position, 1);
			if (mantissa.length() != point_position)
				mantissa.insert(point_position + 1, ".");
			shifted++;
		}

		if (mantissa[mantissa.length() - 1] == '.')
			mantissa += "0";

		while (shifted < shift)
		{
			point_position = mantissa.find_first_of(".");
			mantissa.insert(point_position, "0");
			shifted++;
		}
	}
	else if (shift < 0)
	{
		shifted = 0;
		if (mantissa[0] != '0')
			while (shifted > shift && mantissa[0] != '.')
			{
				point_position = mantissa.find_first_of(".");
				mantissa.erase(point_position, 1);
				mantissa.insert(point_position - 1, ".");
				shifted--;
			}

		if (shifted >= shift && mantissa[0] == '.')
			mantissa = "0" + mantissa;

		while (shifted > shift)
		{
			mantissa.insert(2, "0");
			shifted--;
		}
	}
	return mantissa;
}

string ConvertFloatingNumberToBin(string decimal_num)
{
	string binary = "";

	int point_position = decimal_num.find_first_of(".");
	string int_part = decimal_num.substr(0, point_position);
	string frac_part = decimal_num.substr(point_position + 1, decimal_num.length() + 1 - point_position);

	LongInt integer(int_part);
	LongInt two(2);
	LongInt null(0);

	if (integer == null)
		binary += "0";
	
	while (integer > null)
	{
		binary = (integer%two).longIntToString()+binary;
		integer /= two;
	}

	long long significant_digits = 0;

	if (*(new LongInt(binary)) > null)
		significant_digits += binary.length();	
	binary += ".";

	long long normal_length = frac_part.length();
	while (frac_part[0] == '0'&&frac_part.length() > 1)
		frac_part.erase(0, 1);
	LongInt fractional(frac_part);
	
	while (significant_digits <= MANTISSA_LENGTH)
	{
		fractional *= two;
		if (fractional.longIntToString().length() > normal_length)
		{
			string num = fractional.longIntToString();
			binary += "1";
			num.erase(0, 1);
			while (num[0] == '0'&&num.length() > 1)
				num.erase(0, 1);
			fractional = *(new LongInt(num));
			significant_digits++;
		}
		else
		{
			binary += "0";
			if (significant_digits > 0)
				significant_digits++;
		}
	}
	return binary;
}

string NormalizeBinMantissa(string classical_mantissa, long long& exponent)
{
	string mantissa = classical_mantissa;
	if (mantissa[0] != '0'&&mantissa[1] == '.')
		exponent = 0;
	else if (mantissa[0] != '0')
	{
		int point_position = mantissa.find_first_of(".");
		mantissa.erase(point_position, 1);
		mantissa.insert(1, ".");
		exponent = point_position - mantissa.find_first_of(".");
	}
	else
	{
		exponent = 0;
		while (mantissa[0]=='0'&&mantissa.length()>3)
		{
			mantissa[0] = mantissa[2];
			mantissa.erase(2, 1);
			exponent--;
		}
		if (mantissa.length() == 3)
		{
			mantissa.erase(0, 2);
			mantissa += ".0";
			exponent--;
		}
	}

	return mantissa;
}

string ShiftMantissa(string normalized_mantissa)
{
	//must be declared MANTISSA_LENGTH
	string shifted_normalized_mantissa = normalized_mantissa.erase(0, 2);
	while (shifted_normalized_mantissa.length() < MANTISSA_LENGTH)
		shifted_normalized_mantissa += "0";
	while (shifted_normalized_mantissa.length() > MANTISSA_LENGTH)
		shifted_normalized_mantissa.pop_back();
	return shifted_normalized_mantissa;
}

string Int64ToBin(long long integer)
{
	bitset<EXPONENT_LENGTH> _int64Bits(integer);
	return _int64Bits.to_string<char, std::char_traits<char>, std::allocator<char>>();
}

string GetDecUnusNum(long long num)
{
	long long exp = (long long)(floor(num*log10(2)));
	std::ostringstream ss;
	ss << pow(10, num*log10(2) - exp);
	string mantiss = ss.str();
	mantiss = mantiss.substr(0, 5);
	mantiss += "e";
	ss.str("");
	ss << exp;
	mantiss += ss.str();
	while (mantiss.length() < 12)
		mantiss += " ";

	return mantiss;
}

string getUnusualData()
{
	string output = "";
	output += "NULL              0.0E0       : " + ConvertFloatingToBinaryIEEE754("0.0E0") + '\n';
	output += "MINUS NULL       -0.0E0       : " + ConvertFloatingToBinaryIEEE754("-0.0E0") + '\n';
	output += "INF               INF         : " + ConvertFloatingToBinaryIEEE754("inf") + '\n';
	output += "MINUS INF        -INF         : " + ConvertFloatingToBinaryIEEE754("-inf") + '\n';
	output += "MAX_PLUS          " + GetDecUnusNum(pow(2, EXPONENT_LENGTH - 1) - 1) + ": " + ConvertFloatingToBinaryIEEE754("MAX_PLUS") + '\n';
	output += "MAX_MINUS         " + GetDecUnusNum(-pow(2, EXPONENT_LENGTH - 1) + 2) + ": " + ConvertFloatingToBinaryIEEE754("MAX_MINUS") + '\n';
	output += "MIN_PLUS         -" + GetDecUnusNum(pow(2, EXPONENT_LENGTH - 1) - 1) + ": " + ConvertFloatingToBinaryIEEE754("MIN_PLUS") + '\n';
	output += "MAX_MINUS        -" + GetDecUnusNum(-pow(2, EXPONENT_LENGTH - 1) + 2) + ": " + ConvertFloatingToBinaryIEEE754("MIN_MINUS") + '\n';
	output += "NAN               NAN         : " + ConvertFloatingToBinaryIEEE754("NaN") + '\n';

	return output;
}