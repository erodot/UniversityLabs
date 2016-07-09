#include <iostream>
#include <string>
#include <time.h>
#include <Windows.h>
#include "implementation.h"
#include "auxiliary.h"
#include <cmath>
#include <chrono>
#include <ctime>
#include <ratio>

using namespace std::chrono;

void testChar()
{
	types Char;
	srand(unsigned char(time(NULL)));
	long ITER = CPU_CLOCK_FREQUENCY / FIRST_DIVIDER;
	char a1 = rand() % MULTIPLIER + 2, a2 = rand() % MULTIPLIER + 2, a3 = rand() % MULTIPLIER + 2, a4 = rand() % MULTIPLIER + 2, a5 = rand() % MULTIPLIER + 2, b1 = rand() % MULTIPLIER + 2, b2 = rand() % MULTIPLIER + 2, b3 = rand() % MULTIPLIER + 2, b4 = rand() % MULTIPLIER + 2, b5 = rand() % MULTIPLIER + 2, c1 = rand() % MULTIPLIER + 2, c2 = rand() % MULTIPLIER + 2, c3 = rand() % MULTIPLIER + 2, c4 = rand() % MULTIPLIER + 2, c5 = rand() % MULTIPLIER + 2, d1 = rand() % MULTIPLIER + 2, d2 = rand() % MULTIPLIER + 2, d3 = rand() % MULTIPLIER + 2, d4 = rand() % MULTIPLIER + 2, d5 = rand() % MULTIPLIER + 2, e1 = rand() % MULTIPLIER + 2, e2 = rand() % MULTIPLIER + 2, e3 = rand() % MULTIPLIER + 2, e4 = rand() % MULTIPLIER + 2, e5 = rand() % MULTIPLIER + 2, f1 = rand() % MULTIPLIER + 2, f2 = rand() % MULTIPLIER + 2, f3 = rand() % MULTIPLIER + 2, f4 = rand() % MULTIPLIER + 2, f5 = rand() % MULTIPLIER + 2, g1 = rand() % MULTIPLIER + 2, g2 = rand() % MULTIPLIER + 2, g3 = rand() % MULTIPLIER + 2, g4 = rand() % MULTIPLIER + 2, g5 = rand() % MULTIPLIER + 2, h1 = rand() % MULTIPLIER + 2, h2 = rand() % MULTIPLIER + 2, h3 = rand() % MULTIPLIER + 2, h4 = rand() % MULTIPLIER + 2, h5 = rand() % MULTIPLIER + 2;

	high_resolution_clock::time_point t1;
	high_resolution_clock::time_point t2;

	char temp;

#pragma region CHAR_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2;
		a2 = b2;
		a3 = a4;
		a4 = f4;
		a5 = b5;
		b1 = a1;
		b2 = c2;
		b3 = h3;
		b4 = f2;
		b5 = b2;
		c1 = d1;
		c2 = h2;
		c3 = d4;
		c4 = d4;
		c5 = f1;
		d1 = a4;
		d2 = g2;
		d3 = a1;
		d4 = b4;
		d5 = c5;
		e1 = e2;
		e2 = f3;
		e3 = h1;
		e4 = g1;
		e5 = b1;
		f1 = g3;
		f2 = g4;
		f3 = e3;
		f4 = g1;
		f5 = b5;
		g1 = b1;
		g2 = h2;
		g3 = h3;
		g4 = c2;
		g5 = h5;
		h1 = a1;
		h2 = e2;
		h3 = f3;
		h4 = a4;
		h5 = e5;

	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> char_empty = duration_cast<duration<double>>(t2 - t1);
#pragma endregion 
#pragma region CHAR_MULTIPLY_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		temp = b2; a1 = (temp == 0 ? 5 : temp);
		temp = b2; a2 = (temp == 0 ? 7 : temp);
		temp = a4; a3 = (temp == 0 ? 11 : temp);
		temp = f4; a4 = (temp == 0 ? 13 : temp);
		temp = b5; a5 = (temp == 0 ? 17 : temp);
		temp = a1; b1 = (temp == 0 ? 19 : temp);
		temp = c2; b2 = (temp == 0 ? 23 : temp);
		temp = h3; b3 = (temp == 0 ? 27 : temp);
		temp = f2; b4 = (temp == 0 ? 29 : temp);
		temp = b2; b5 = (temp == 0 ? 31 : temp);
		temp = d1; c1 = (temp == 0 ? 37 : temp);
		temp = h2; c2 = (temp == 0 ? 41 : temp);
		temp = d4; c3 = (temp == 0 ? 43 : temp);
		temp = d4; c4 = (temp == 0 ? 47 : temp);
		temp = f1; c5 = (temp == 0 ? 5 : temp);
		temp = a4; d1 = (temp == 0 ? 7 : temp);
		temp = g2; d2 = (temp == 0 ? 11 : temp);
		temp = a1; d3 = (temp == 0 ? 13 : temp);
		temp = b4; d4 = (temp == 0 ? 17 : temp);
		temp = c5; d5 = (temp == 0 ? 19 : temp);
		temp = e2; e1 = (temp == 0 ? 23 : temp);
		temp = f3; e2 = (temp == 0 ? 27 : temp);
		temp = h1; e3 = (temp == 0 ? 29 : temp);
		temp = g1; e4 = (temp == 0 ? 31 : temp);
		temp = b1; e5 = (temp == 0 ? 37 : temp);
		temp = g3; f1 = (temp == 0 ? 41 : temp);
		temp = g4; f2 = (temp == 0 ? 43 : temp);
		temp = e3; f3 = (temp == 0 ? 47 : temp);
		temp = g1; f4 = (temp == 0 ? 5 : temp);
		temp = b5; f5 = (temp == 0 ? 7 : temp);
		temp = b1; g1 = (temp == 0 ? 11 : temp);
		temp = h2; g2 = (temp == 0 ? 13 : temp);
		temp = h3; g3 = (temp == 0 ? 17 : temp);
		temp = c2; g4 = (temp == 0 ? 19 : temp);
		temp = h5; g5 = (temp == 0 ? 23 : temp);
		temp = a1; h1 = (temp == 0 ? 27 : temp);
		temp = e2; h2 = (temp == 0 ? 29 : temp);
		temp = f3; h3 = (temp == 0 ? 31 : temp);
		temp = a4; h4 = (temp == 0 ? 41 : temp);
		temp = e5; h5 = (temp == 0 ? 43 : temp);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> char_multiply_empty = duration_cast<duration<double>>(t2 - t1);

#pragma endregion
#pragma region CHAR_DIVIDE_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = (c2 == 0 ? 5 : c2);
		a2 = (d2 == 0 ? 7 : d2);
		a3 = (c3 == 0 ? 11 : c3);
		a4 = (d5 == 0 ? 13 : d5);
		a5 = (g3 == 0 ? 17 : g3);
		b1 = (b3 == 0 ? 19 : b3);
		b2 = (d5 == 0 ? 23 : d5);
		b3 = (e3 == 0 ? 27 : e3);
		b4 = (a4 == 0 ? 29 : a4);
		b5 = (d5 == 0 ? 31 : d5);
		c1 = (e5 == 0 ? 37 : e5);
		c2 = (f2 == 0 ? 41 : f2);
		c3 = (a3 == 0 ? 43 : a3);
		c4 = (c1 == 0 ? 47 : c1);
		c5 = (f5 == 0 ? 5 : f5);
		d1 = (a1 == 0 ? 7 : a1);
		d2 = (f3 == 0 ? 11 : f3);
		d3 = (b5 == 0 ? 13 : b5);
		d4 = (h4 == 0 ? 17 : h4);
		d5 = (b1 == 0 ? 19 : b1);
		e1 = (e3 == 0 ? 23 : e3);
		e2 = (g2 == 0 ? 27 : g2);
		e3 = (h3 == 0 ? 29 : h3);
		e4 = (a4 == 0 ? 31 : a4);
		e5 = (c5 == 0 ? 37 : c5);
		f1 = (h5 == 0 ? 41 : h5);
		f2 = (h1 == 0 ? 43 : h1);
		f3 = (e5 == 0 ? 47 : e5);
		f4 = (h4 == 0 ? 5 : h4);
		f5 = (c2 == 0 ? 7 : c2);
		g1 = (e2 == 0 ? 11 : e2);
		g2 = (e3 == 0 ? 13 : e3);
		g3 = (e4 == 0 ? 17 : e4);
		g4 = (e5 == 0 ? 19 : e5);
		g5 = (e5 == 0 ? 23 : e5);
		h1 = (b1 == 0 ? 27 : b1);
		h2 = (f5 == 0 ? 29 : f5);
		h3 = (f2 == 0 ? 31 : f2);
		h4 = (a1 == 0 ? 41 : a1);
		h5 = (h5 == 0 ? 43 : h5);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> char_divide_empty = duration_cast<duration<double>>(t2 - t1);

#pragma endregion

#pragma region CHAR+

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2 + c2;
		a2 = b2 + d2;
		a3 = a4 + c3;
		a4 = f4 + d5;
		a5 = b5 + g3;
		b1 = a1 + b3;
		b2 = c2 + d5;
		b3 = h3 + e3;
		b4 = f2 + a4;
		b5 = b2 + d5;
		c1 = d1 + e5;
		c2 = h2 + f2;
		c3 = d4 + a3;
		c4 = d4 + c1;
		c5 = f1 + f5;
		d1 = a4 + a1;
		d2 = g2 + f3;
		d3 = a1 + b5;
		d4 = b4 + h4;
		d5 = c5 + b1;
		e1 = e2 + e3;
		e2 = f3 + g2;
		e3 = h1 + h3;
		e4 = g1 + a4;
		e5 = b1 + c5;
		f1 = g3 + h5;
		f2 = g4 + h1;
		f3 = e3 + e5;
		f4 = g1 + h4;
		f5 = b5 + c2;
		g1 = b1 + e2;
		g2 = h2 + e3;
		g3 = h3 + e4;
		g4 = c2 + e5;
		g5 = h5 + e5;
		h1 = a1 + b1;
		h2 = e2 + f5;
		h3 = f3 + f2;
		h4 = a4 + a1;
		h5 = e5 + h5;
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> char_plus = duration_cast<duration<double>>(t2 - t1);

	Char.Plus = FIRST_DIVIDER * double(ITER) / (char_plus.count() - char_empty.count());

#pragma endregion
#pragma region CHAR-

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2 - c2;
		a2 = b2 - d2;
		a3 = a4 - c3;
		a4 = f4 - d5;
		a5 = b5 - g3;
		b1 = a1 - b3;
		b2 = c2 - d5;
		b3 = h3 - e3;
		b4 = f2 - a4;
		b5 = b2 - d5;
		c1 = d1 - e5;
		c2 = h2 - f2;
		c3 = d4 - a3;
		c4 = d4 - c1;
		c5 = f1 - f5;
		d1 = a4 - a1;
		d2 = g2 - f3;
		d3 = a1 - b5;
		d4 = b4 - h4;
		d5 = c5 - b1;
		e1 = e2 - e3;
		e2 = f3 - g2;
		e3 = h1 - h3;
		e4 = g1 - a4;
		e5 = b1 - c5;
		f1 = g3 - h5;
		f2 = g4 - h1;
		f3 = e3 - e5;
		f4 = g1 - h4;
		f5 = b5 - c2;
		g1 = b1 - e2;
		g2 = h2 - e3;
		g3 = h3 - e4;
		g4 = c2 - e5;
		g5 = h5 - e5;
		h1 = a1 - b1;
		h2 = e2 - f5;
		h3 = f3 - f2;
		h4 = a4 - a1;
		h5 = e5 - h5;
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> char_minus = duration_cast<duration<double>>(t2 - t1);

	Char.Minus = FIRST_DIVIDER * double(ITER) / (char_minus.count() - char_empty.count());

#pragma endregion
#pragma region CHAR*

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		temp = b2*c2; a1 = (temp == 0 ? 5 : temp);
		temp = b2*d2; a2 = (temp == 0 ? 7 : temp);
		temp = a4*c3; a3 = (temp == 0 ? 11 : temp);
		temp = f4*d5; a4 = (temp == 0 ? 13 : temp);
		temp = b5*g3; a5 = (temp == 0 ? 17 : temp);
		temp = a1*b3; b1 = (temp == 0 ? 19 : temp);
		temp = c2*d5; b2 = (temp == 0 ? 23 : temp);
		temp = h3*e3; b3 = (temp == 0 ? 27 : temp);
		temp = f2*a4; b4 = (temp == 0 ? 29 : temp);
		temp = b2*d5; b5 = (temp == 0 ? 31 : temp);
		temp = d1*e5; c1 = (temp == 0 ? 37 : temp);
		temp = h2*f2; c2 = (temp == 0 ? 41 : temp);
		temp = d4*a3; c3 = (temp == 0 ? 43 : temp);
		temp = d4*c1; c4 = (temp == 0 ? 47 : temp);
		temp = f1*f5; c5 = (temp == 0 ? 5 : temp);
		temp = a4*a1; d1 = (temp == 0 ? 7 : temp);
		temp = g2*f3; d2 = (temp == 0 ? 11 : temp);
		temp = a1*b5; d3 = (temp == 0 ? 13 : temp);
		temp = b4*h4; d4 = (temp == 0 ? 17 : temp);
		temp = c5*d1; d5 = (temp == 0 ? 19 : temp);
		temp = e2*e3; e1 = (temp == 0 ? 23 : temp);
		temp = f3*g2; e2 = (temp == 0 ? 27 : temp);
		temp = h1*h3; e3 = (temp == 0 ? 29 : temp);
		temp = g1*a4; e4 = (temp == 0 ? 31 : temp);
		temp = b1*c5; e5 = (temp == 0 ? 37 : temp);
		temp = g3*h5; f1 = (temp == 0 ? 41 : temp);
		temp = g4*h1; f2 = (temp == 0 ? 43 : temp);
		temp = e3*e5; f3 = (temp == 0 ? 47 : temp);
		temp = g1*h4; f4 = (temp == 0 ? 5 : temp);
		temp = b5*c2; f5 = (temp == 0 ? 7 : temp);
		temp = b1*e2; g1 = (temp == 0 ? 11 : temp);
		temp = h2*e3; g2 = (temp == 0 ? 13 : temp);
		temp = h3*e4; g3 = (temp == 0 ? 17 : temp);
		temp = c2*e5; g4 = (temp == 0 ? 19 : temp);
		temp = h5*e5; g5 = (temp == 0 ? 23 : temp);
		temp = a1*b2; h1 = (temp == 0 ? 27 : temp);
		temp = e2*f5; h2 = (temp == 0 ? 29 : temp);
		temp = f3*f2; h3 = (temp == 0 ? 31 : temp);
		temp = a4*a1; h4 = (temp == 0 ? 41 : temp);
		temp = e5*h5; h5 = (temp == 0 ? 43 : temp);

	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> char_multiply = duration_cast<duration<double>>(t2 - t1);

	Char.Multiply = FIRST_DIVIDER * double(ITER) / (char_multiply.count() - char_multiply_empty.count());

#pragma endregion
#pragma region CHAR/

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = a2 / (c2 == 0 ? 5 : c2);
		a2 = b2 / (d2 == 0 ? 7 : d2);
		a3 = a4 / (c3 == 0 ? 11 : c3);
		a4 = f4 / (d5 == 0 ? 13 : d5);
		a5 = b5 / (g3 == 0 ? 17 : g3);
		b1 = a1 / (b3 == 0 ? 19 : b3);
		b2 = c2 / (d5 == 0 ? 23 : d5);
		b3 = h3 / (e3 == 0 ? 27 : e3);
		b4 = f2 / (a4 == 0 ? 29 : a4);
		b5 = b2 / (d5 == 0 ? 31 : d5);
		c1 = d1 / (e5 == 0 ? 37 : e5);
		c2 = h2 / (f2 == 0 ? 41 : f2);
		c3 = d4 / (a3 == 0 ? 43 : a3);
		c4 = d4 / (c1 == 0 ? 47 : c1);
		c5 = f1 / (f5 == 0 ? 5 : f5);
		d1 = a4 / (a1 == 0 ? 7 : a1);
		d2 = g2 / (f3 == 0 ? 11 : f3);
		d3 = a1 / (b5 == 0 ? 13 : b5);
		d4 = b4 / (h4 == 0 ? 17 : h4);
		d5 = c5 / (b1 == 0 ? 19 : b1);
		e1 = e2 / (e3 == 0 ? 23 : e3);
		e2 = f3 / (g2 == 0 ? 27 : g2);
		e3 = h1 / (h3 == 0 ? 29 : h3);
		e4 = g1 / (a4 == 0 ? 31 : a4);
		e5 = b1 / (c5 == 0 ? 37 : c5);
		f1 = g3 / (h5 == 0 ? 41 : h5);
		f2 = g4 / (h1 == 0 ? 43 : h1);
		f3 = e3 / (e5 == 0 ? 47 : e5);
		f4 = g1 / (h4 == 0 ? 5 : h4);
		f5 = b5 / (c2 == 0 ? 7 : c2);
		g1 = b1 / (e2 == 0 ? 11 : e2);
		g2 = h2 / (e3 == 0 ? 13 : e3);
		g3 = h3 / (e4 == 0 ? 17 : e4);
		g4 = c2 / (e5 == 0 ? 19 : e5);
		g5 = h5 / (e5 == 0 ? 23 : e5);
		h1 = a1 / (b1 == 0 ? 27 : b1);
		h2 = e2 / (f5 == 0 ? 29 : f5);
		h3 = f3 / (f2 == 0 ? 31 : f2);
		h4 = a4 / (a1 == 0 ? 41 : a1);
		h5 = e5 / (h5 == 0 ? 43 : h5);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> char_divide = duration_cast<duration<double>>(t2 - t1);

	Char.Divide = FIRST_DIVIDER * double(ITER) / (char_divide.count() - char_divide_empty.count());

#pragma endregion

	SetMax(Char);

	Show("+", "char", Char.Plus, Char.Max);
	Show("-", "char", Char.Minus, Char.Max);
	Show("*", "char", Char.Multiply, Char.Max);
	Show("/", "char", Char.Divide, Char.Max);
	cout << '\n';
}

void testInt()
{
	types Int;
	srand(unsigned int(time(NULL)));
	long ITER = CPU_CLOCK_FREQUENCY / FIRST_DIVIDER;
	int a1 = rand() % MULTIPLIER + 2, a2 = rand() % MULTIPLIER + 2, a3 = rand() % MULTIPLIER + 2, a4 = rand() % MULTIPLIER + 2, a5 = rand() % MULTIPLIER + 2, b1 = rand() % MULTIPLIER + 2, b2 = rand() % MULTIPLIER + 2, b3 = rand() % MULTIPLIER + 2, b4 = rand() % MULTIPLIER + 2, b5 = rand() % MULTIPLIER + 2, c1 = rand() % MULTIPLIER + 2, c2 = rand() % MULTIPLIER + 2, c3 = rand() % MULTIPLIER + 2, c4 = rand() % MULTIPLIER + 2, c5 = rand() % MULTIPLIER + 2, d1 = rand() % MULTIPLIER + 2, d2 = rand() % MULTIPLIER + 2, d3 = rand() % MULTIPLIER + 2, d4 = rand() % MULTIPLIER + 2, d5 = rand() % MULTIPLIER + 2, e1 = rand() % MULTIPLIER + 2, e2 = rand() % MULTIPLIER + 2, e3 = rand() % MULTIPLIER + 2, e4 = rand() % MULTIPLIER + 2, e5 = rand() % MULTIPLIER + 2, f1 = rand() % MULTIPLIER + 2, f2 = rand() % MULTIPLIER + 2, f3 = rand() % MULTIPLIER + 2, f4 = rand() % MULTIPLIER + 2, f5 = rand() % MULTIPLIER + 2, g1 = rand() % MULTIPLIER + 2, g2 = rand() % MULTIPLIER + 2, g3 = rand() % MULTIPLIER + 2, g4 = rand() % MULTIPLIER + 2, g5 = rand() % MULTIPLIER + 2, h1 = rand() % MULTIPLIER + 2, h2 = rand() % MULTIPLIER + 2, h3 = rand() % MULTIPLIER + 2, h4 = rand() % MULTIPLIER + 2, h5 = rand() % MULTIPLIER + 2;

	high_resolution_clock::time_point t1;
	high_resolution_clock::time_point t2;

	int temp;

#pragma region INT_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2;
		a2 = b2;
		a3 = a4;
		a4 = f4;
		a5 = b5;
		b1 = a1;
		b2 = c2;
		b3 = h3;
		b4 = f2;
		b5 = b2;
		c1 = d1;
		c2 = h2;
		c3 = d4;
		c4 = d4;
		c5 = f1;
		d1 = a4;
		d2 = g2;
		d3 = a1;
		d4 = b4;
		d5 = c5;
		e1 = e2;
		e2 = f3;
		e3 = h1;
		e4 = g1;
		e5 = b1;
		f1 = g3;
		f2 = g4;
		f3 = e3;
		f4 = g1;
		f5 = b5;
		g1 = b1;
		g2 = h2;
		g3 = h3;
		g4 = c2;
		g5 = h5;
		h1 = a1;
		h2 = e2;
		h3 = f3;
		h4 = a4;
		h5 = e5;

	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> int_empty = duration_cast<duration<double>>(t2 - t1);
#pragma endregion 
#pragma region INT_MULTIPLY_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		temp = b2; a1 = (temp == 0 ? 5 : temp);
		temp = b2; a2 = (temp == 0 ? 7 : temp);
		temp = a4; a3 = (temp == 0 ? 11 : temp);
		temp = f4; a4 = (temp == 0 ? 13 : temp);
		temp = b5; a5 = (temp == 0 ? 17 : temp);
		temp = a1; b1 = (temp == 0 ? 19 : temp);
		temp = c2; b2 = (temp == 0 ? 23 : temp);
		temp = h3; b3 = (temp == 0 ? 27 : temp);
		temp = f2; b4 = (temp == 0 ? 29 : temp);
		temp = b2; b5 = (temp == 0 ? 31 : temp);
		temp = d1; c1 = (temp == 0 ? 37 : temp);
		temp = h2; c2 = (temp == 0 ? 41 : temp);
		temp = d4; c3 = (temp == 0 ? 43 : temp);
		temp = d4; c4 = (temp == 0 ? 47 : temp);
		temp = f1; c5 = (temp == 0 ? 5 : temp);
		temp = a4; d1 = (temp == 0 ? 7 : temp);
		temp = g2; d2 = (temp == 0 ? 11 : temp);
		temp = a1; d3 = (temp == 0 ? 13 : temp);
		temp = b4; d4 = (temp == 0 ? 17 : temp);
		temp = c5; d5 = (temp == 0 ? 19 : temp);
		temp = e2; e1 = (temp == 0 ? 23 : temp);
		temp = f3; e2 = (temp == 0 ? 27 : temp);
		temp = h1; e3 = (temp == 0 ? 29 : temp);
		temp = g1; e4 = (temp == 0 ? 31 : temp);
		temp = b1; e5 = (temp == 0 ? 37 : temp);
		temp = g3; f1 = (temp == 0 ? 41 : temp);
		temp = g4; f2 = (temp == 0 ? 43 : temp);
		temp = e3; f3 = (temp == 0 ? 47 : temp);
		temp = g1; f4 = (temp == 0 ? 5 : temp);
		temp = b5; f5 = (temp == 0 ? 7 : temp);
		temp = b1; g1 = (temp == 0 ? 11 : temp);
		temp = h2; g2 = (temp == 0 ? 13 : temp);
		temp = h3; g3 = (temp == 0 ? 17 : temp);
		temp = c2; g4 = (temp == 0 ? 19 : temp);
		temp = h5; g5 = (temp == 0 ? 23 : temp);
		temp = a1; h1 = (temp == 0 ? 27 : temp);
		temp = e2; h2 = (temp == 0 ? 29 : temp);
		temp = f3; h3 = (temp == 0 ? 31 : temp);
		temp = a4; h4 = (temp == 0 ? 41 : temp);
		temp = e5; h5 = (temp == 0 ? 43 : temp);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> int_multiply_empty = duration_cast<duration<double>>(t2 - t1);

#pragma endregion
#pragma region INT_DIVIDE_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = (c2 == 0 ? 5 : c2);
		a2 = (d2 == 0 ? 7 : d2);
		a3 = (c3 == 0 ? 11 : c3);
		a4 = (d5 == 0 ? 13 : d5);
		a5 = (g3 == 0 ? 17 : g3);
		b1 = (b3 == 0 ? 19 : b3);
		b2 = (d5 == 0 ? 23 : d5);
		b3 = (e3 == 0 ? 27 : e3);
		b4 = (a4 == 0 ? 29 : a4);
		b5 = (d5 == 0 ? 31 : d5);
		c1 = (e5 == 0 ? 37 : e5);
		c2 = (f2 == 0 ? 41 : f2);
		c3 = (a3 == 0 ? 43 : a3);
		c4 = (c1 == 0 ? 47 : c1);
		c5 = (f5 == 0 ? 5 : f5);
		d1 = (a1 == 0 ? 7 : a1);
		d2 = (f3 == 0 ? 11 : f3);
		d3 = (b5 == 0 ? 13 : b5);
		d4 = (h4 == 0 ? 17 : h4);
		d5 = (b1 == 0 ? 19 : b1);
		e1 = (e3 == 0 ? 23 : e3);
		e2 = (g2 == 0 ? 27 : g2);
		e3 = (h3 == 0 ? 29 : h3);
		e4 = (a4 == 0 ? 31 : a4);
		e5 = (c5 == 0 ? 37 : c5);
		f1 = (h5 == 0 ? 41 : h5);
		f2 = (h1 == 0 ? 43 : h1);
		f3 = (e5 == 0 ? 47 : e5);
		f4 = (h4 == 0 ? 5 : h4);
		f5 = (c2 == 0 ? 7 : c2);
		g1 = (e2 == 0 ? 11 : e2);
		g2 = (e3 == 0 ? 13 : e3);
		g3 = (e4 == 0 ? 17 : e4);
		g4 = (e5 == 0 ? 19 : e5);
		g5 = (e5 == 0 ? 23 : e5);
		h1 = (b1 == 0 ? 27 : b1);
		h2 = (f5 == 0 ? 29 : f5);
		h3 = (f2 == 0 ? 31 : f2);
		h4 = (a1 == 0 ? 41 : a1);
		h5 = (h5 == 0 ? 43 : h5);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> int_divide_empty = duration_cast<duration<double>>(t2 - t1);

#pragma endregion

#pragma region INT+

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2 + c2;
		a2 = b2 + d2;
		a3 = a4 + c3;
		a4 = f4 + d5;
		a5 = b5 + g3;
		b1 = a1 + b3;
		b2 = c2 + d5;
		b3 = h3 + e3;
		b4 = f2 + a4;
		b5 = b2 + d5;
		c1 = d1 + e5;
		c2 = h2 + f2;
		c3 = d4 + a3;
		c4 = d4 + c1;
		c5 = f1 + f5;
		d1 = a4 + a1;
		d2 = g2 + f3;
		d3 = a1 + b5;
		d4 = b4 + h4;
		d5 = c5 + b1;
		e1 = e2 + e3;
		e2 = f3 + g2;
		e3 = h1 + h3;
		e4 = g1 + a4;
		e5 = b1 + c5;
		f1 = g3 + h5;
		f2 = g4 + h1;
		f3 = e3 + e5;
		f4 = g1 + h4;
		f5 = b5 + c2;
		g1 = b1 + e2;
		g2 = h2 + e3;
		g3 = h3 + e4;
		g4 = c2 + e5;
		g5 = h5 + e5;
		h1 = a1 + b1;
		h2 = e2 + f5;
		h3 = f3 + f2;
		h4 = a4 + a1;
		h5 = e5 + h5;
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> int_plus = duration_cast<duration<double>>(t2 - t1);

	Int.Plus = FIRST_DIVIDER * double(ITER) / (int_plus.count() - int_empty.count());

#pragma endregion
#pragma region INT-

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2 - c2;
		a2 = b2 - d2;
		a3 = a4 - c3;
		a4 = f4 - d5;
		a5 = b5 - g3;
		b1 = a1 - b3;
		b2 = c2 - d5;
		b3 = h3 - e3;
		b4 = f2 - a4;
		b5 = b2 - d5;
		c1 = d1 - e5;
		c2 = h2 - f2;
		c3 = d4 - a3;
		c4 = d4 - c1;
		c5 = f1 - f5;
		d1 = a4 - a1;
		d2 = g2 - f3;
		d3 = a1 - b5;
		d4 = b4 - h4;
		d5 = c5 - b1;
		e1 = e2 - e3;
		e2 = f3 - g2;
		e3 = h1 - h3;
		e4 = g1 - a4;
		e5 = b1 - c5;
		f1 = g3 - h5;
		f2 = g4 - h1;
		f3 = e3 - e5;
		f4 = g1 - h4;
		f5 = b5 - c2;
		g1 = b1 - e2;
		g2 = h2 - e3;
		g3 = h3 - e4;
		g4 = c2 - e5;
		g5 = h5 - e5;
		h1 = a1 - b1;
		h2 = e2 - f5;
		h3 = f3 - f2;
		h4 = a4 - a1;
		h5 = e5 - h5;
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> int_minus = duration_cast<duration<double>>(t2 - t1);

	Int.Minus = FIRST_DIVIDER * double(ITER) / (int_minus.count() - int_empty.count());

#pragma endregion
#pragma region INT*

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		temp = b2*c2; a1 = (temp == 0 ? 5 : temp);
		temp = b2*d2; a2 = (temp == 0 ? 7 : temp);
		temp = a4*c3; a3 = (temp == 0 ? 11 : temp);
		temp = f4*d5; a4 = (temp == 0 ? 13 : temp);
		temp = b5*g3; a5 = (temp == 0 ? 17 : temp);
		temp = a1*b3; b1 = (temp == 0 ? 19 : temp);
		temp = c2*d5; b2 = (temp == 0 ? 23 : temp);
		temp = h3*e3; b3 = (temp == 0 ? 27 : temp);
		temp = f2*a4; b4 = (temp == 0 ? 29 : temp);
		temp = b2*d5; b5 = (temp == 0 ? 31 : temp);
		temp = d1*e5; c1 = (temp == 0 ? 37 : temp);
		temp = h2*f2; c2 = (temp == 0 ? 41 : temp);
		temp = d4*a3; c3 = (temp == 0 ? 43 : temp);
		temp = d4*c1; c4 = (temp == 0 ? 47 : temp);
		temp = f1*f5; c5 = (temp == 0 ? 5 : temp);
		temp = a4*a1; d1 = (temp == 0 ? 7 : temp);
		temp = g2*f3; d2 = (temp == 0 ? 11 : temp);
		temp = a1*b5; d3 = (temp == 0 ? 13 : temp);
		temp = b4*h4; d4 = (temp == 0 ? 17 : temp);
		temp = c5*d1; d5 = (temp == 0 ? 19 : temp);
		temp = e2*e3; e1 = (temp == 0 ? 23 : temp);
		temp = f3*g2; e2 = (temp == 0 ? 27 : temp);
		temp = h1*h3; e3 = (temp == 0 ? 29 : temp);
		temp = g1*a4; e4 = (temp == 0 ? 31 : temp);
		temp = b1*c5; e5 = (temp == 0 ? 37 : temp);
		temp = g3*h5; f1 = (temp == 0 ? 41 : temp);
		temp = g4*h1; f2 = (temp == 0 ? 43 : temp);
		temp = e3*e5; f3 = (temp == 0 ? 47 : temp);
		temp = g1*h4; f4 = (temp == 0 ? 5 : temp);
		temp = b5*c2; f5 = (temp == 0 ? 7 : temp);
		temp = b1*e2; g1 = (temp == 0 ? 11 : temp);
		temp = h2*e3; g2 = (temp == 0 ? 13 : temp);
		temp = h3*e4; g3 = (temp == 0 ? 17 : temp);
		temp = c2*e5; g4 = (temp == 0 ? 19 : temp);
		temp = h5*e5; g5 = (temp == 0 ? 23 : temp);
		temp = a1*b2; h1 = (temp == 0 ? 27 : temp);
		temp = e2*f5; h2 = (temp == 0 ? 29 : temp);
		temp = f3*f2; h3 = (temp == 0 ? 31 : temp);
		temp = a4*a1; h4 = (temp == 0 ? 41 : temp);
		temp = e5*h5; h5 = (temp == 0 ? 43 : temp);

	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> int_multiply = duration_cast<duration<double>>(t2 - t1);

	Int.Multiply = FIRST_DIVIDER * double(ITER) / (int_multiply.count() - int_multiply_empty.count());

#pragma endregion
#pragma region INT/

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = a2 / (c2 == 0 ? 5 : c2);
		a2 = b2 / (d2 == 0 ? 7 : d2);
		a3 = a4 / (c3 == 0 ? 11 : c3);
		a4 = f4 / (d5 == 0 ? 13 : d5);
		a5 = b5 / (g3 == 0 ? 17 : g3);
		b1 = a1 / (b3 == 0 ? 19 : b3);
		b2 = c2 / (d5 == 0 ? 23 : d5);
		b3 = h3 / (e3 == 0 ? 27 : e3);
		b4 = f2 / (a4 == 0 ? 29 : a4);
		b5 = b2 / (d5 == 0 ? 31 : d5);
		c1 = d1 / (e5 == 0 ? 37 : e5);
		c2 = h2 / (f2 == 0 ? 41 : f2);
		c3 = d4 / (a3 == 0 ? 43 : a3);
		c4 = d4 / (c1 == 0 ? 47 : c1);
		c5 = f1 / (f5 == 0 ? 5 : f5);
		d1 = a4 / (a1 == 0 ? 7 : a1);
		d2 = g2 / (f3 == 0 ? 11 : f3);
		d3 = a1 / (b5 == 0 ? 13 : b5);
		d4 = b4 / (h4 == 0 ? 17 : h4);
		d5 = c5 / (b1 == 0 ? 19 : b1);
		e1 = e2 / (e3 == 0 ? 23 : e3);
		e2 = f3 / (g2 == 0 ? 27 : g2);
		e3 = h1 / (h3 == 0 ? 29 : h3);
		e4 = g1 / (a4 == 0 ? 31 : a4);
		e5 = b1 / (c5 == 0 ? 37 : c5);
		f1 = g3 / (h5 == 0 ? 41 : h5);
		f2 = g4 / (h1 == 0 ? 43 : h1);
		f3 = e3 / (e5 == 0 ? 47 : e5);
		f4 = g1 / (h4 == 0 ? 5 : h4);
		f5 = b5 / (c2 == 0 ? 7 : c2);
		g1 = b1 / (e2 == 0 ? 11 : e2);
		g2 = h2 / (e3 == 0 ? 13 : e3);
		g3 = h3 / (e4 == 0 ? 17 : e4);
		g4 = c2 / (e5 == 0 ? 19 : e5);
		g5 = h5 / (e5 == 0 ? 23 : e5);
		h1 = a1 / (b1 == 0 ? 27 : b1);
		h2 = e2 / (f5 == 0 ? 29 : f5);
		h3 = f3 / (f2 == 0 ? 31 : f2);
		h4 = a4 / (a1 == 0 ? 41 : a1);
		h5 = e5 / (h5 == 0 ? 43 : h5);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> int_divide = duration_cast<duration<double>>(t2 - t1);

	Int.Divide = FIRST_DIVIDER * double(ITER) / (int_divide.count() - int_divide_empty.count());

#pragma endregion

	SetMax(Int);

	Show("+", "int", Int.Plus, Int.Max);
	Show("-", "int", Int.Minus, Int.Max);
	Show("*", "int", Int.Multiply, Int.Max);
	Show("/", "int", Int.Divide, Int.Max);
	cout << '\n';
}

void testLong()
{
	types Long;
	srand(unsigned int(time(NULL)));
	long ITER = CPU_CLOCK_FREQUENCY / FIRST_DIVIDER;
	long a1 = rand() % MULTIPLIER + 2, a2 = rand() % MULTIPLIER + 2, a3 = rand() % MULTIPLIER + 2, a4 = rand() % MULTIPLIER + 2, a5 = rand() % MULTIPLIER + 2, b1 = rand() % MULTIPLIER + 2, b2 = rand() % MULTIPLIER + 2, b3 = rand() % MULTIPLIER + 2, b4 = rand() % MULTIPLIER + 2, b5 = rand() % MULTIPLIER + 2, c1 = rand() % MULTIPLIER + 2, c2 = rand() % MULTIPLIER + 2, c3 = rand() % MULTIPLIER + 2, c4 = rand() % MULTIPLIER + 2, c5 = rand() % MULTIPLIER + 2, d1 = rand() % MULTIPLIER + 2, d2 = rand() % MULTIPLIER + 2, d3 = rand() % MULTIPLIER + 2, d4 = rand() % MULTIPLIER + 2, d5 = rand() % MULTIPLIER + 2, e1 = rand() % MULTIPLIER + 2, e2 = rand() % MULTIPLIER + 2, e3 = rand() % MULTIPLIER + 2, e4 = rand() % MULTIPLIER + 2, e5 = rand() % MULTIPLIER + 2, f1 = rand() % MULTIPLIER + 2, f2 = rand() % MULTIPLIER + 2, f3 = rand() % MULTIPLIER + 2, f4 = rand() % MULTIPLIER + 2, f5 = rand() % MULTIPLIER + 2, g1 = rand() % MULTIPLIER + 2, g2 = rand() % MULTIPLIER + 2, g3 = rand() % MULTIPLIER + 2, g4 = rand() % MULTIPLIER + 2, g5 = rand() % MULTIPLIER + 2, h1 = rand() % MULTIPLIER + 2, h2 = rand() % MULTIPLIER + 2, h3 = rand() % MULTIPLIER + 2, h4 = rand() % MULTIPLIER + 2, h5 = rand() % MULTIPLIER + 2;

	high_resolution_clock::time_point t1;
	high_resolution_clock::time_point t2;

	long temp;

#pragma region LONG_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2;
		a2 = b2;
		a3 = a4;
		a4 = f4;
		a5 = b5;
		b1 = a1;
		b2 = c2;
		b3 = h3;
		b4 = f2;
		b5 = b2;
		c1 = d1;
		c2 = h2;
		c3 = d4;
		c4 = d4;
		c5 = f1;
		d1 = a4;
		d2 = g2;
		d3 = a1;
		d4 = b4;
		d5 = c5;
		e1 = e2;
		e2 = f3;
		e3 = h1;
		e4 = g1;
		e5 = b1;
		f1 = g3;
		f2 = g4;
		f3 = e3;
		f4 = g1;
		f5 = b5;
		g1 = b1;
		g2 = h2;
		g3 = h3;
		g4 = c2;
		g5 = h5;
		h1 = a1;
		h2 = e2;
		h3 = f3;
		h4 = a4;
		h5 = e5;

	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> long_empty = duration_cast<duration<double>>(t2 - t1);
#pragma endregion 
#pragma region LONG_MULTIPLY_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		temp = b2; a1 = (temp == 0 ? 5 : temp);
		temp = b2; a2 = (temp == 0 ? 7 : temp);
		temp = a4; a3 = (temp == 0 ? 11 : temp);
		temp = f4; a4 = (temp == 0 ? 13 : temp);
		temp = b5; a5 = (temp == 0 ? 17 : temp);
		temp = a1; b1 = (temp == 0 ? 19 : temp);
		temp = c2; b2 = (temp == 0 ? 23 : temp);
		temp = h3; b3 = (temp == 0 ? 27 : temp);
		temp = f2; b4 = (temp == 0 ? 29 : temp);
		temp = b2; b5 = (temp == 0 ? 31 : temp);
		temp = d1; c1 = (temp == 0 ? 37 : temp);
		temp = h2; c2 = (temp == 0 ? 41 : temp);
		temp = d4; c3 = (temp == 0 ? 43 : temp);
		temp = d4; c4 = (temp == 0 ? 47 : temp);
		temp = f1; c5 = (temp == 0 ? 5 : temp);
		temp = a4; d1 = (temp == 0 ? 7 : temp);
		temp = g2; d2 = (temp == 0 ? 11 : temp);
		temp = a1; d3 = (temp == 0 ? 13 : temp);
		temp = b4; d4 = (temp == 0 ? 17 : temp);
		temp = c5; d5 = (temp == 0 ? 19 : temp);
		temp = e2; e1 = (temp == 0 ? 23 : temp);
		temp = f3; e2 = (temp == 0 ? 27 : temp);
		temp = h1; e3 = (temp == 0 ? 29 : temp);
		temp = g1; e4 = (temp == 0 ? 31 : temp);
		temp = b1; e5 = (temp == 0 ? 37 : temp);
		temp = g3; f1 = (temp == 0 ? 41 : temp);
		temp = g4; f2 = (temp == 0 ? 43 : temp);
		temp = e3; f3 = (temp == 0 ? 47 : temp);
		temp = g1; f4 = (temp == 0 ? 5 : temp);
		temp = b5; f5 = (temp == 0 ? 7 : temp);
		temp = b1; g1 = (temp == 0 ? 11 : temp);
		temp = h2; g2 = (temp == 0 ? 13 : temp);
		temp = h3; g3 = (temp == 0 ? 17 : temp);
		temp = c2; g4 = (temp == 0 ? 19 : temp);
		temp = h5; g5 = (temp == 0 ? 23 : temp);
		temp = a1; h1 = (temp == 0 ? 27 : temp);
		temp = e2; h2 = (temp == 0 ? 29 : temp);
		temp = f3; h3 = (temp == 0 ? 31 : temp);
		temp = a4; h4 = (temp == 0 ? 41 : temp);
		temp = e5; h5 = (temp == 0 ? 43 : temp);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> long_multiply_empty = duration_cast<duration<double>>(t2 - t1);

#pragma endregion
#pragma region LONG_DIVIDE_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = (c2 == 0 ? 5 : c2);
		a2 = (d2 == 0 ? 7 : d2);
		a3 = (c3 == 0 ? 11 : c3);
		a4 = (d5 == 0 ? 13 : d5);
		a5 = (g3 == 0 ? 17 : g3);
		b1 = (b3 == 0 ? 19 : b3);
		b2 = (d5 == 0 ? 23 : d5);
		b3 = (e3 == 0 ? 27 : e3);
		b4 = (a4 == 0 ? 29 : a4);
		b5 = (d5 == 0 ? 31 : d5);
		c1 = (e5 == 0 ? 37 : e5);
		c2 = (f2 == 0 ? 41 : f2);
		c3 = (a3 == 0 ? 43 : a3);
		c4 = (c1 == 0 ? 47 : c1);
		c5 = (f5 == 0 ? 5 : f5);
		d1 = (a1 == 0 ? 7 : a1);
		d2 = (f3 == 0 ? 11 : f3);
		d3 = (b5 == 0 ? 13 : b5);
		d4 = (h4 == 0 ? 17 : h4);
		d5 = (b1 == 0 ? 19 : b1);
		e1 = (e3 == 0 ? 23 : e3);
		e2 = (g2 == 0 ? 27 : g2);
		e3 = (h3 == 0 ? 29 : h3);
		e4 = (a4 == 0 ? 31 : a4);
		e5 = (c5 == 0 ? 37 : c5);
		f1 = (h5 == 0 ? 41 : h5);
		f2 = (h1 == 0 ? 43 : h1);
		f3 = (e5 == 0 ? 47 : e5);
		f4 = (h4 == 0 ? 5 : h4);
		f5 = (c2 == 0 ? 7 : c2);
		g1 = (e2 == 0 ? 11 : e2);
		g2 = (e3 == 0 ? 13 : e3);
		g3 = (e4 == 0 ? 17 : e4);
		g4 = (e5 == 0 ? 19 : e5);
		g5 = (e5 == 0 ? 23 : e5);
		h1 = (b1 == 0 ? 27 : b1);
		h2 = (f5 == 0 ? 29 : f5);
		h3 = (f2 == 0 ? 31 : f2);
		h4 = (a1 == 0 ? 41 : a1);
		h5 = (h5 == 0 ? 43 : h5);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> long_divide_empty = duration_cast<duration<double>>(t2 - t1);

#pragma endregion

#pragma region LONG+

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2 + c2;
		a2 = b2 + d2;
		a3 = a4 + c3;
		a4 = f4 + d5;
		a5 = b5 + g3;
		b1 = a1 + b3;
		b2 = c2 + d5;
		b3 = h3 + e3;
		b4 = f2 + a4;
		b5 = b2 + d5;
		c1 = d1 + e5;
		c2 = h2 + f2;
		c3 = d4 + a3;
		c4 = d4 + c1;
		c5 = f1 + f5;
		d1 = a4 + a1;
		d2 = g2 + f3;
		d3 = a1 + b5;
		d4 = b4 + h4;
		d5 = c5 + b1;
		e1 = e2 + e3;
		e2 = f3 + g2;
		e3 = h1 + h3;
		e4 = g1 + a4;
		e5 = b1 + c5;
		f1 = g3 + h5;
		f2 = g4 + h1;
		f3 = e3 + e5;
		f4 = g1 + h4;
		f5 = b5 + c2;
		g1 = b1 + e2;
		g2 = h2 + e3;
		g3 = h3 + e4;
		g4 = c2 + e5;
		g5 = h5 + e5;
		h1 = a1 + b1;
		h2 = e2 + f5;
		h3 = f3 + f2;
		h4 = a4 + a1;
		h5 = e5 + h5;
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> long_plus = duration_cast<duration<double>>(t2 - t1);

	Long.Plus = FIRST_DIVIDER * double(ITER) / (long_plus.count() - long_empty.count());

#pragma endregion
#pragma region LONG-

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b2 - c2;
		a2 = b2 - d2;
		a3 = a4 - c3;
		a4 = f4 - d5;
		a5 = b5 - g3;
		b1 = a1 - b3;
		b2 = c2 - d5;
		b3 = h3 - e3;
		b4 = f2 - a4;
		b5 = b2 - d5;
		c1 = d1 - e5;
		c2 = h2 - f2;
		c3 = d4 - a3;
		c4 = d4 - c1;
		c5 = f1 - f5;
		d1 = a4 - a1;
		d2 = g2 - f3;
		d3 = a1 - b5;
		d4 = b4 - h4;
		d5 = c5 - b1;
		e1 = e2 - e3;
		e2 = f3 - g2;
		e3 = h1 - h3;
		e4 = g1 - a4;
		e5 = b1 - c5;
		f1 = g3 - h5;
		f2 = g4 - h1;
		f3 = e3 - e5;
		f4 = g1 - h4;
		f5 = b5 - c2;
		g1 = b1 - e2;
		g2 = h2 - e3;
		g3 = h3 - e4;
		g4 = c2 - e5;
		g5 = h5 - e5;
		h1 = a1 - b1;
		h2 = e2 - f5;
		h3 = f3 - f2;
		h4 = a4 - a1;
		h5 = e5 - h5;
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> long_minus = duration_cast<duration<double>>(t2 - t1);

	Long.Minus = FIRST_DIVIDER * double(ITER) / (long_minus.count() - long_empty.count());

#pragma endregion
#pragma region LONG*

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		temp = b2*c2; a1 = (temp == 0 ? 5 : temp);
		temp = b2*d2; a2 = (temp == 0 ? 7 : temp);
		temp = a4*c3; a3 = (temp == 0 ? 11 : temp);
		temp = f4*d5; a4 = (temp == 0 ? 13 : temp);
		temp = b5*g3; a5 = (temp == 0 ? 17 : temp);
		temp = a1*b3; b1 = (temp == 0 ? 19 : temp);
		temp = c2*d5; b2 = (temp == 0 ? 23 : temp);
		temp = h3*e3; b3 = (temp == 0 ? 27 : temp);
		temp = f2*a4; b4 = (temp == 0 ? 29 : temp);
		temp = b2*d5; b5 = (temp == 0 ? 31 : temp);
		temp = d1*e5; c1 = (temp == 0 ? 37 : temp);
		temp = h2*f2; c2 = (temp == 0 ? 41 : temp);
		temp = d4*a3; c3 = (temp == 0 ? 43 : temp);
		temp = d4*c1; c4 = (temp == 0 ? 47 : temp);
		temp = f1*f5; c5 = (temp == 0 ? 5 : temp);
		temp = a4*a1; d1 = (temp == 0 ? 7 : temp);
		temp = g2*f3; d2 = (temp == 0 ? 11 : temp);
		temp = a1*b5; d3 = (temp == 0 ? 13 : temp);
		temp = b4*h4; d4 = (temp == 0 ? 17 : temp);
		temp = c5*d1; d5 = (temp == 0 ? 19 : temp);
		temp = e2*e3; e1 = (temp == 0 ? 23 : temp);
		temp = f3*g2; e2 = (temp == 0 ? 27 : temp);
		temp = h1*h3; e3 = (temp == 0 ? 29 : temp);
		temp = g1*a4; e4 = (temp == 0 ? 31 : temp);
		temp = b1*c5; e5 = (temp == 0 ? 37 : temp);
		temp = g3*h5; f1 = (temp == 0 ? 41 : temp);
		temp = g4*h1; f2 = (temp == 0 ? 43 : temp);
		temp = e3*e5; f3 = (temp == 0 ? 47 : temp);
		temp = g1*h4; f4 = (temp == 0 ? 5 : temp);
		temp = b5*c2; f5 = (temp == 0 ? 7 : temp);
		temp = b1*e2; g1 = (temp == 0 ? 11 : temp);
		temp = h2*e3; g2 = (temp == 0 ? 13 : temp);
		temp = h3*e4; g3 = (temp == 0 ? 17 : temp);
		temp = c2*e5; g4 = (temp == 0 ? 19 : temp);
		temp = h5*e5; g5 = (temp == 0 ? 23 : temp);
		temp = a1*b2; h1 = (temp == 0 ? 27 : temp);
		temp = e2*f5; h2 = (temp == 0 ? 29 : temp);
		temp = f3*f2; h3 = (temp == 0 ? 31 : temp);
		temp = a4*a1; h4 = (temp == 0 ? 41 : temp);
		temp = e5*h5; h5 = (temp == 0 ? 43 : temp);

	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> long_multiply = duration_cast<duration<double>>(t2 - t1);

	Long.Multiply = FIRST_DIVIDER * double(ITER) / (long_multiply.count() - long_multiply_empty.count());

#pragma endregion
#pragma region LONG/

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = a2 / (c2 == 0 ? 5 : c2);
		a2 = b2 / (d2 == 0 ? 7 : d2);
		a3 = a4 / (c3 == 0 ? 11 : c3);
		a4 = f4 / (d5 == 0 ? 13 : d5);
		a5 = b5 / (g3 == 0 ? 17 : g3);
		b1 = a1 / (b3 == 0 ? 19 : b3);
		b2 = c2 / (d5 == 0 ? 23 : d5);
		b3 = h3 / (e3 == 0 ? 27 : e3);
		b4 = f2 / (a4 == 0 ? 29 : a4);
		b5 = b2 / (d5 == 0 ? 31 : d5);
		c1 = d1 / (e5 == 0 ? 37 : e5);
		c2 = h2 / (f2 == 0 ? 41 : f2);
		c3 = d4 / (a3 == 0 ? 43 : a3);
		c4 = d4 / (c1 == 0 ? 47 : c1);
		c5 = f1 / (f5 == 0 ? 5 : f5);
		d1 = a4 / (a1 == 0 ? 7 : a1);
		d2 = g2 / (f3 == 0 ? 11 : f3);
		d3 = a1 / (b5 == 0 ? 13 : b5);
		d4 = b4 / (h4 == 0 ? 17 : h4);
		d5 = c5 / (b1 == 0 ? 19 : b1);
		e1 = e2 / (e3 == 0 ? 23 : e3);
		e2 = f3 / (g2 == 0 ? 27 : g2);
		e3 = h1 / (h3 == 0 ? 29 : h3);
		e4 = g1 / (a4 == 0 ? 31 : a4);
		e5 = b1 / (c5 == 0 ? 37 : c5);
		f1 = g3 / (h5 == 0 ? 41 : h5);
		f2 = g4 / (h1 == 0 ? 43 : h1);
		f3 = e3 / (e5 == 0 ? 47 : e5);
		f4 = g1 / (h4 == 0 ? 5 : h4);
		f5 = b5 / (c2 == 0 ? 7 : c2);
		g1 = b1 / (e2 == 0 ? 11 : e2);
		g2 = h2 / (e3 == 0 ? 13 : e3);
		g3 = h3 / (e4 == 0 ? 17 : e4);
		g4 = c2 / (e5 == 0 ? 19 : e5);
		g5 = h5 / (e5 == 0 ? 23 : e5);
		h1 = a1 / (b1 == 0 ? 27 : b1);
		h2 = e2 / (f5 == 0 ? 29 : f5);
		h3 = f3 / (f2 == 0 ? 31 : f2);
		h4 = a4 / (a1 == 0 ? 41 : a1);
		h5 = e5 / (h5 == 0 ? 43 : h5);
	}
	t2 = std::chrono::high_resolution_clock::now();

	duration<double> long_divide = duration_cast<duration<double>>(t2 - t1);

	Long.Divide = FIRST_DIVIDER * double(ITER) / (long_divide.count() - long_divide_empty.count());

#pragma endregion

	SetMax(Long);

	Show("+", "long", Long.Plus, Long.Max);
	Show("-", "long", Long.Minus, Long.Max);
	Show("*", "long", Long.Multiply, Long.Max);
	Show("/", "long", Long.Divide, Long.Max);
	cout << '\n';
}

void testFloat()
{
	types Float;
	srand(unsigned int(time(NULL)));
	long ITER = CPU_CLOCK_FREQUENCY / FIRST_DIVIDER /REAL_DIVIDER;
	float a1 = float(rand() % MULTIPLIER + 2), a2 = float(rand() % MULTIPLIER + 2), a3 = float(rand() % MULTIPLIER + 2), a4 = float(rand() % MULTIPLIER + 2), a5 = float(rand() % MULTIPLIER + 2), b1 = float(rand() % MULTIPLIER + 2), b2 = float(rand() % MULTIPLIER + 2), b3 = float(rand() % MULTIPLIER + 2), b4 = float(rand() % MULTIPLIER + 2), b5 = float(rand() % MULTIPLIER + 2), c1 = float(rand() % MULTIPLIER + 2), c2 = float(rand() % MULTIPLIER + 2), c3 = float(rand() % MULTIPLIER + 2), c4 = float(rand() % MULTIPLIER + 2), c5 = float(rand() % MULTIPLIER + 2), d1 = float(rand() % MULTIPLIER + 2), d2 = float(rand() % MULTIPLIER + 2), d3 = float(rand() % MULTIPLIER + 2), d4 = float(rand() % MULTIPLIER + 2), d5 = float(rand() % MULTIPLIER + 2), e1 = float(rand() % MULTIPLIER + 2), e2 = float(rand() % MULTIPLIER + 2), e3 = float(rand() % MULTIPLIER + 2), e4 = float(rand() % MULTIPLIER + 2), e5 = float(rand() % MULTIPLIER + 2), f1 = float(rand() % MULTIPLIER + 2), f2 = float(rand() % MULTIPLIER + 2), f3 = float(rand() % MULTIPLIER + 2), f4 = float(rand() % MULTIPLIER + 2), f5 = float(rand() % MULTIPLIER + 2), g1 = float(rand() % MULTIPLIER + 2), g2 = float(rand() % MULTIPLIER + 2), g3 = float(rand() % MULTIPLIER + 2), g4 = float(rand() % MULTIPLIER + 2), g5 = float(rand() % MULTIPLIER + 2), h1 = float(rand() % MULTIPLIER + 2), h2 = float(rand() % MULTIPLIER + 2), h3 = float(rand() % MULTIPLIER + 2), h4 = float(rand() % MULTIPLIER + 2), h5 = float(rand() % MULTIPLIER + 2);
	high_resolution_clock::time_point t1;
	high_resolution_clock::time_point t2;

#pragma region FLOAT_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1, MULTIPLIER) + 2;
		a2 = fmod(b2, MULTIPLIER) + 3;
		a3 = fmod(a4, MULTIPLIER) + 4;
		a4 = fmod(f4, MULTIPLIER) + 5;
		a5 = fmod(b5, MULTIPLIER) + 6;
		b1 = fmod(a1, MULTIPLIER) + 7;
		b2 = fmod(c2, MULTIPLIER) + 8;
		b3 = fmod(h3, MULTIPLIER) + 9;
		b4 = fmod(f2, MULTIPLIER) + 2;
		b5 = fmod(b2, MULTIPLIER) + 3;
		c1 = fmod(d1, MULTIPLIER) + 4;
		c2 = fmod(h2, MULTIPLIER) + 5;
		c3 = fmod(d4, MULTIPLIER) + 6;
		c4 = fmod(d4, MULTIPLIER) + 7;
		c5 = fmod(f1, MULTIPLIER) + 8;
		d1 = fmod(a4, MULTIPLIER) + 9;
		d2 = fmod(g2, MULTIPLIER) + 2;
		d3 = fmod(a1, MULTIPLIER) + 3;
		d4 = fmod(b4, MULTIPLIER) + 4;
		d5 = fmod(c5, MULTIPLIER) + 5;
		e1 = fmod(e2, MULTIPLIER) + 6;
		e2 = fmod(f3, MULTIPLIER) + 7;
		e3 = fmod(h1, MULTIPLIER) + 8;
		e4 = fmod(g1, MULTIPLIER) + 9;
		e5 = fmod(b1, MULTIPLIER) + 2;
		f1 = fmod(g3, MULTIPLIER) + 3;
		f2 = fmod(g4, MULTIPLIER) + 4;
		f3 = fmod(e3, MULTIPLIER) + 5;
		f4 = fmod(g1, MULTIPLIER) + 6;
		f5 = fmod(b5, MULTIPLIER) + 7;
		g1 = fmod(b1, MULTIPLIER) + 8;
		g2 = fmod(h2, MULTIPLIER) + 9;
		g3 = fmod(h3, MULTIPLIER) + 2;
		g4 = fmod(c2, MULTIPLIER) + 3;
		g5 = fmod(h5, MULTIPLIER) + 4;
		h1 = fmod(a1, MULTIPLIER) + 5;
		h2 = fmod(e2, MULTIPLIER) + 6;
		h3 = fmod(f3, MULTIPLIER) + 7;
		h4 = fmod(a4, MULTIPLIER) + 8;
		h5 = fmod(e5, MULTIPLIER) + 9;
	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> float_empty = duration_cast<duration<double>>(t2 - t1);
#pragma endregion 

#pragma region FLOAT+

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1 + c2, MULTIPLIER) + 2;
		a2 = fmod(b2 + d2, MULTIPLIER) + 3;
		a3 = fmod(a4 + c3, MULTIPLIER) + 4;
		a4 = fmod(f4 + d5, MULTIPLIER) + 5;
		a5 = fmod(b5 + g3, MULTIPLIER) + 6;
		b1 = fmod(a1 + b3, MULTIPLIER) + 7;
		b2 = fmod(c2 + d5, MULTIPLIER) + 8;
		b3 = fmod(h3 + e3, MULTIPLIER) + 9;
		b4 = fmod(f2 + a4, MULTIPLIER) + 2;
		b5 = fmod(b2 + d5, MULTIPLIER) + 3;
		c1 = fmod(d1 + e5, MULTIPLIER) + 4;
		c2 = fmod(h2 + f2, MULTIPLIER) + 5;
		c3 = fmod(d4 + a3, MULTIPLIER) + 6;
		c4 = fmod(d4 + c1, MULTIPLIER) + 7;
		c5 = fmod(f1 + f5, MULTIPLIER) + 8;
		d1 = fmod(a4 + a1, MULTIPLIER) + 9;
		d2 = fmod(g2 + f3, MULTIPLIER) + 2;
		d3 = fmod(a1 + b5, MULTIPLIER) + 3;
		d4 = fmod(b4 + h4, MULTIPLIER) + 4;
		d5 = fmod(c5 + b1, MULTIPLIER) + 5;
		e1 = fmod(e2 + e3, MULTIPLIER) + 6;
		e2 = fmod(f3 + g2, MULTIPLIER) + 7;
		e3 = fmod(h1 + h3, MULTIPLIER) + 8;
		e4 = fmod(g1 + a4, MULTIPLIER) + 9;
		e5 = fmod(b1 + c5, MULTIPLIER) + 2;
		f1 = fmod(g3 + h5, MULTIPLIER) + 3;
		f2 = fmod(g4 + h1, MULTIPLIER) + 4;
		f3 = fmod(e3 + e5, MULTIPLIER) + 5;
		f4 = fmod(g1 + h4, MULTIPLIER) + 6;
		f5 = fmod(b5 + c2, MULTIPLIER) + 7;
		g1 = fmod(b1 + e2, MULTIPLIER) + 8;
		g2 = fmod(h2 + e3, MULTIPLIER) + 9;
		g3 = fmod(h3 + e4, MULTIPLIER) + 2;
		g4 = fmod(c2 + e5, MULTIPLIER) + 3;
		g5 = fmod(h5 + e5, MULTIPLIER) + 4;
		h1 = fmod(a1 + b1, MULTIPLIER) + 5;
		h2 = fmod(e2 + f5, MULTIPLIER) + 6;
		h3 = fmod(f3 + f2, MULTIPLIER) + 7;
		h4 = fmod(a4 + a1, MULTIPLIER) + 8;
		h5 = fmod(e5 + h5, MULTIPLIER) + 9;
	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> float_plus = duration_cast<duration<double>>(t2 - t1);

	t1 = std::chrono::high_resolution_clock::now();

	Float.Plus = FIRST_DIVIDER * double(ITER) / (float_plus.count() - float_empty.count());

#pragma endregion
#pragma region FLOAT-

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1 - c2, MULTIPLIER) + 2;
		a2 = fmod(b2 - d2, MULTIPLIER) + 3;
		a3 = fmod(a4 - c3, MULTIPLIER) + 4;
		a4 = fmod(f4 - d5, MULTIPLIER) + 5;
		a5 = fmod(b5 - g3, MULTIPLIER) + 6;
		b1 = fmod(a1 - b3, MULTIPLIER) + 7;
		b2 = fmod(c2 - d5, MULTIPLIER) + 8;
		b3 = fmod(h3 - e3, MULTIPLIER) + 9;
		b4 = fmod(f2 - a4, MULTIPLIER) + 2;
		b5 = fmod(b2 - d5, MULTIPLIER) + 3;
		c1 = fmod(d1 - e5, MULTIPLIER) + 4;
		c2 = fmod(h2 - f2, MULTIPLIER) + 5;
		c3 = fmod(d4 - a3, MULTIPLIER) + 6;
		c4 = fmod(d4 - c1, MULTIPLIER) + 7;
		c5 = fmod(f1 - f5, MULTIPLIER) + 8;
		d1 = fmod(a4 - a1, MULTIPLIER) + 9;
		d2 = fmod(g2 - f3, MULTIPLIER) + 2;
		d3 = fmod(a1 - b5, MULTIPLIER) + 3;
		d4 = fmod(b4 - h4, MULTIPLIER) + 4;
		d5 = fmod(c5 - b1, MULTIPLIER) + 5;
		e1 = fmod(e2 - e3, MULTIPLIER) + 6;
		e2 = fmod(f3 - g2, MULTIPLIER) + 7;
		e3 = fmod(h1 - h3, MULTIPLIER) + 8;
		e4 = fmod(g1 - a4, MULTIPLIER) + 9;
		e5 = fmod(b1 - c5, MULTIPLIER) + 2;
		f1 = fmod(g3 - h5, MULTIPLIER) + 3;
		f2 = fmod(g4 - h1, MULTIPLIER) + 4;
		f3 = fmod(e3 - e5, MULTIPLIER) + 5;
		f4 = fmod(g1 - h4, MULTIPLIER) + 6;
		f5 = fmod(b5 - c2, MULTIPLIER) + 7;
		g1 = fmod(b1 - e2, MULTIPLIER) + 8;
		g2 = fmod(h2 - e3, MULTIPLIER) + 9;
		g3 = fmod(h3 - e4, MULTIPLIER) + 2;
		g4 = fmod(c2 - e5, MULTIPLIER) + 3;
		g5 = fmod(h5 - e5, MULTIPLIER) + 4;
		h1 = fmod(a1 - b1, MULTIPLIER) + 5;
		h2 = fmod(e2 - f5, MULTIPLIER) + 6;
		h3 = fmod(f3 - f2, MULTIPLIER) + 7;
		h4 = fmod(a4 - a1, MULTIPLIER) + 8;
		h5 = fmod(e5 - h5, MULTIPLIER) + 9;

	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> float_minus = duration_cast<duration<double>>(t2 - t1);

	Float.Minus = FIRST_DIVIDER * double(ITER) / (float_minus.count() - float_empty.count());

#pragma endregion
#pragma region FLOAT*

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1 * c2, MULTIPLIER) + 2;
		a2 = fmod(b2 * d2, MULTIPLIER) + 3;
		a3 = fmod(a4 * c3, MULTIPLIER) + 4;
		a4 = fmod(f4 * d5, MULTIPLIER) + 5;
		a5 = fmod(b5 * g3, MULTIPLIER) + 6;
		b1 = fmod(a1 * b3, MULTIPLIER) + 7;
		b2 = fmod(c2 * d5, MULTIPLIER) + 8;
		b3 = fmod(h3 * e3, MULTIPLIER) + 9;
		b4 = fmod(f2 * a4, MULTIPLIER) + 2;
		b5 = fmod(b2 * d5, MULTIPLIER) + 3;
		c1 = fmod(d1 * e5, MULTIPLIER) + 4;
		c2 = fmod(h2 * f2, MULTIPLIER) + 5;
		c3 = fmod(d4 * a3, MULTIPLIER) + 6;
		c4 = fmod(d4 * c1, MULTIPLIER) + 7;
		c5 = fmod(f1 * f5, MULTIPLIER) + 8;
		d1 = fmod(a4 * a1, MULTIPLIER) + 9;
		d2 = fmod(g2 * f3, MULTIPLIER) + 2;
		d3 = fmod(a1 * b5, MULTIPLIER) + 3;
		d4 = fmod(b4 * h4, MULTIPLIER) + 4;
		d5 = fmod(c5 * b1, MULTIPLIER) + 5;
		e1 = fmod(e2 * e3, MULTIPLIER) + 6;
		e2 = fmod(f3 * g2, MULTIPLIER) + 7;
		e3 = fmod(h1 * h3, MULTIPLIER) + 8;
		e4 = fmod(g1 * a4, MULTIPLIER) + 9;
		e5 = fmod(b1 * c5, MULTIPLIER) + 2;
		f1 = fmod(g3 * h5, MULTIPLIER) + 3;
		f2 = fmod(g4 * h1, MULTIPLIER) + 4;
		f3 = fmod(e3 * e5, MULTIPLIER) + 5;
		f4 = fmod(g1 * h4, MULTIPLIER) + 6;
		f5 = fmod(b5 * c2, MULTIPLIER) + 7;
		g1 = fmod(b1 * e2, MULTIPLIER) + 8;
		g2 = fmod(h2 * e3, MULTIPLIER) + 9;
		g3 = fmod(h3 * e4, MULTIPLIER) + 2;
		g4 = fmod(c2 * e5, MULTIPLIER) + 3;
		g5 = fmod(h5 * e5, MULTIPLIER) + 4;
		h1 = fmod(a1 * b1, MULTIPLIER) + 5;
		h2 = fmod(e2 * f5, MULTIPLIER) + 6;
		h3 = fmod(f3 * f2, MULTIPLIER) + 7;
		h4 = fmod(a4 * a1, MULTIPLIER) + 8;
		h5 = fmod(e5 * h5, MULTIPLIER) + 9;

	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> float_multiply = duration_cast<duration<double>>(t2 - t1);

	Float.Multiply = FIRST_DIVIDER * double(ITER) / (float_multiply.count() - float_empty.count());

#pragma endregion
#pragma region FLOAT/

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b1 + 2;
		a2 = b2 + 3;
		a3 = a4 + 4;
		a4 = f4 + 5;
		a5 = b5 + 6;
		b1 = a1 + 7;
		b2 = c2 + 8;
		b3 = h3 + 9;
		b4 = f2 + 2;
		b5 = b2 + 3;
		c1 = d1 + 4;
		c2 = h2 + 5;
		c3 = d4 + 6;
		c4 = d4 + 7;
		c5 = f1 + 8;
		d1 = a4 + 9;
		d2 = g2 + 2;
		d3 = a1 + 3;
		d4 = b4 + 4;
		d5 = c5 + 5;
		e1 = e2 + 6;
		e2 = f3 + 7;
		e3 = h1 + 8;
		e4 = g1 + 9;
		e5 = b1 + 2;
		f1 = g3 + 3;
		f2 = g4 + 4;
		f3 = e3 + 5;
		f4 = g1 + 6;
		f5 = b5 + 7;
		g1 = b1 + 8;
		g2 = h2 + 9;
		g3 = h3 + 2;
		g4 = c2 + 3;
		g5 = h5 + 4;
		h1 = a1 + 5;
		h2 = e2 + 6;
		h3 = f3 + 7;
		h4 = a4 + 8;
		h5 = e5 + 9;
	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> float_divide_empty = duration_cast<duration<double>>(t2 - t1);

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b1 / c2 + 2;
		a2 = b2 / d2 + 3;
		a3 = a4 / c3 + 4;
		a4 = f4 / d5 + 5;
		a5 = b5 / g3 + 6;
		b1 = a1 / b3 + 7;
		b2 = c2 / d5 + 8;
		b3 = h3 / e3 + 9;
		b4 = f2 / a4 + 2;
		b5 = b2 / d5 + 3;
		c1 = d1 / e5 + 4;
		c2 = h2 / f2 + 5;
		c3 = d4 / a3 + 6;
		c4 = d4 / c1 + 7;
		c5 = f1 / f5 + 8;
		d1 = a4 / a1 + 9;
		d2 = g2 / f3 + 2;
		d3 = a1 / b5 + 3;
		d4 = b4 / h4 + 4;
		d5 = c5 / b1 + 5;
		e1 = e2 / e3 + 6;
		e2 = f3 / g2 + 7;
		e3 = h1 / h3 + 8;
		e4 = g1 / a4 + 9;
		e5 = b1 / c5 + 2;
		f1 = g3 / h5 + 3;
		f2 = g4 / h1 + 4;
		f3 = e3 / e5 + 5;
		f4 = g1 / h4 + 6;
		f5 = b5 / c2 + 7;
		g1 = b1 / e2 + 8;
		g2 = h2 / e3 + 9;
		g3 = h3 / e4 + 2;
		g4 = c2 / e5 + 3;
		g5 = h5 / e5 + 4;
		h1 = a1 / b1 + 5;
		h2 = e2 / f5 + 6;
		h3 = f3 / f2 + 7;
		h4 = a4 / a1 + 8;
		h5 = e5 / h5 + 9;

	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> float_divide = duration_cast<duration<double>>(t2 - t1);

	Float.Divide = FIRST_DIVIDER * double(ITER) / (float_divide.count() - float_divide_empty.count());

#pragma endregion

	SetMax(Float);

	Show("+", "float", Float.Plus, Float.Max);
	Show("-", "float", Float.Minus, Float.Max);
	Show("*", "float", Float.Multiply, Float.Max);
	Show("/", "float", Float.Divide, Float.Max);
	cout << "\n";
}

void testDouble()
{
	types Double;
	srand(unsigned int(time(NULL)));
	long ITER = CPU_CLOCK_FREQUENCY / FIRST_DIVIDER / REAL_DIVIDER;
	double a1 = double(rand() % MULTIPLIER + 2), a2 = double(rand() % MULTIPLIER + 2), a3 = double(rand() % MULTIPLIER + 2), a4 = double(rand() % MULTIPLIER + 2), a5 = double(rand() % MULTIPLIER + 2), b1 = double(rand() % MULTIPLIER + 2), b2 = double(rand() % MULTIPLIER + 2), b3 = double(rand() % MULTIPLIER + 2), b4 = double(rand() % MULTIPLIER + 2), b5 = double(rand() % MULTIPLIER + 2), c1 = double(rand() % MULTIPLIER + 2), c2 = double(rand() % MULTIPLIER + 2), c3 = double(rand() % MULTIPLIER + 2), c4 = double(rand() % MULTIPLIER + 2), c5 = double(rand() % MULTIPLIER + 2), d1 = double(rand() % MULTIPLIER + 2), d2 = double(rand() % MULTIPLIER + 2), d3 = double(rand() % MULTIPLIER + 2), d4 = double(rand() % MULTIPLIER + 2), d5 = double(rand() % MULTIPLIER + 2), e1 = double(rand() % MULTIPLIER + 2), e2 = double(rand() % MULTIPLIER + 2), e3 = double(rand() % MULTIPLIER + 2), e4 = double(rand() % MULTIPLIER + 2), e5 = double(rand() % MULTIPLIER + 2), f1 = double(rand() % MULTIPLIER + 2), f2 = double(rand() % MULTIPLIER + 2), f3 = double(rand() % MULTIPLIER + 2), f4 = double(rand() % MULTIPLIER + 2), f5 = double(rand() % MULTIPLIER + 2), g1 = double(rand() % MULTIPLIER + 2), g2 = double(rand() % MULTIPLIER + 2), g3 = double(rand() % MULTIPLIER + 2), g4 = double(rand() % MULTIPLIER + 2), g5 = double(rand() % MULTIPLIER + 2), h1 = double(rand() % MULTIPLIER + 2), h2 = double(rand() % MULTIPLIER + 2), h3 = double(rand() % MULTIPLIER + 2), h4 = double(rand() % MULTIPLIER + 2), h5 = double(rand() % MULTIPLIER + 2);
	high_resolution_clock::time_point t1;
	high_resolution_clock::time_point t2;

#pragma region DOUBLE_EMPTY

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1, MULTIPLIER) + 2;
		a2 = fmod(b2, MULTIPLIER) + 3;
		a3 = fmod(a4, MULTIPLIER) + 4;
		a4 = fmod(f4, MULTIPLIER) + 5;
		a5 = fmod(b5, MULTIPLIER) + 6;
		b1 = fmod(a1, MULTIPLIER) + 7;
		b2 = fmod(c2, MULTIPLIER) + 8;
		b3 = fmod(h3, MULTIPLIER) + 9;
		b4 = fmod(f2, MULTIPLIER) + 2;
		b5 = fmod(b2, MULTIPLIER) + 3;
		c1 = fmod(d1, MULTIPLIER) + 4;
		c2 = fmod(h2, MULTIPLIER) + 5;
		c3 = fmod(d4, MULTIPLIER) + 6;
		c4 = fmod(d4, MULTIPLIER) + 7;
		c5 = fmod(f1, MULTIPLIER) + 8;
		d1 = fmod(a4, MULTIPLIER) + 9;
		d2 = fmod(g2, MULTIPLIER) + 2;
		d3 = fmod(a1, MULTIPLIER) + 3;
		d4 = fmod(b4, MULTIPLIER) + 4;
		d5 = fmod(c5, MULTIPLIER) + 5;
		e1 = fmod(e2, MULTIPLIER) + 6;
		e2 = fmod(f3, MULTIPLIER) + 7;
		e3 = fmod(h1, MULTIPLIER) + 8;
		e4 = fmod(g1, MULTIPLIER) + 9;
		e5 = fmod(b1, MULTIPLIER) + 2;
		f1 = fmod(g3, MULTIPLIER) + 3;
		f2 = fmod(g4, MULTIPLIER) + 4;
		f3 = fmod(e3, MULTIPLIER) + 5;
		f4 = fmod(g1, MULTIPLIER) + 6;
		f5 = fmod(b5, MULTIPLIER) + 7;
		g1 = fmod(b1, MULTIPLIER) + 8;
		g2 = fmod(h2, MULTIPLIER) + 9;
		g3 = fmod(h3, MULTIPLIER) + 2;
		g4 = fmod(c2, MULTIPLIER) + 3;
		g5 = fmod(h5, MULTIPLIER) + 4;
		h1 = fmod(a1, MULTIPLIER) + 5;
		h2 = fmod(e2, MULTIPLIER) + 6;
		h3 = fmod(f3, MULTIPLIER) + 7;
		h4 = fmod(a4, MULTIPLIER) + 8;
		h5 = fmod(e5, MULTIPLIER) + 9;
	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> double_empty = duration_cast<duration<double>>(t2 - t1);
#pragma endregion 

#pragma region DOUBLE+

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1 + c2, MULTIPLIER) + 2;
		a2 = fmod(b2 + d2, MULTIPLIER) + 3;
		a3 = fmod(a4 + c3, MULTIPLIER) + 4;
		a4 = fmod(f4 + d5, MULTIPLIER) + 5;
		a5 = fmod(b5 + g3, MULTIPLIER) + 6;
		b1 = fmod(a1 + b3, MULTIPLIER) + 7;
		b2 = fmod(c2 + d5, MULTIPLIER) + 8;
		b3 = fmod(h3 + e3, MULTIPLIER) + 9;
		b4 = fmod(f2 + a4, MULTIPLIER) + 2;
		b5 = fmod(b2 + d5, MULTIPLIER) + 3;
		c1 = fmod(d1 + e5, MULTIPLIER) + 4;
		c2 = fmod(h2 + f2, MULTIPLIER) + 5;
		c3 = fmod(d4 + a3, MULTIPLIER) + 6;
		c4 = fmod(d4 + c1, MULTIPLIER) + 7;
		c5 = fmod(f1 + f5, MULTIPLIER) + 8;
		d1 = fmod(a4 + a1, MULTIPLIER) + 9;
		d2 = fmod(g2 + f3, MULTIPLIER) + 2;
		d3 = fmod(a1 + b5, MULTIPLIER) + 3;
		d4 = fmod(b4 + h4, MULTIPLIER) + 4;
		d5 = fmod(c5 + b1, MULTIPLIER) + 5;
		e1 = fmod(e2 + e3, MULTIPLIER) + 6;
		e2 = fmod(f3 + g2, MULTIPLIER) + 7;
		e3 = fmod(h1 + h3, MULTIPLIER) + 8;
		e4 = fmod(g1 + a4, MULTIPLIER) + 9;
		e5 = fmod(b1 + c5, MULTIPLIER) + 2;
		f1 = fmod(g3 + h5, MULTIPLIER) + 3;
		f2 = fmod(g4 + h1, MULTIPLIER) + 4;
		f3 = fmod(e3 + e5, MULTIPLIER) + 5;
		f4 = fmod(g1 + h4, MULTIPLIER) + 6;
		f5 = fmod(b5 + c2, MULTIPLIER) + 7;
		g1 = fmod(b1 + e2, MULTIPLIER) + 8;
		g2 = fmod(h2 + e3, MULTIPLIER) + 9;
		g3 = fmod(h3 + e4, MULTIPLIER) + 2;
		g4 = fmod(c2 + e5, MULTIPLIER) + 3;
		g5 = fmod(h5 + e5, MULTIPLIER) + 4;
		h1 = fmod(a1 + b1, MULTIPLIER) + 5;
		h2 = fmod(e2 + f5, MULTIPLIER) + 6;
		h3 = fmod(f3 + f2, MULTIPLIER) + 7;
		h4 = fmod(a4 + a1, MULTIPLIER) + 8;
		h5 = fmod(e5 + h5, MULTIPLIER) + 9;
	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> double_plus = duration_cast<duration<double>>(t2 - t1);

	t1 = std::chrono::high_resolution_clock::now();

	Double.Plus = FIRST_DIVIDER * double(ITER) / (double_plus.count() - double_empty.count());

#pragma endregion
#pragma region DOUBLE-

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1 - c2, MULTIPLIER) + 2;
		a2 = fmod(b2 - d2, MULTIPLIER) + 3;
		a3 = fmod(a4 - c3, MULTIPLIER) + 4;
		a4 = fmod(f4 - d5, MULTIPLIER) + 5;
		a5 = fmod(b5 - g3, MULTIPLIER) + 6;
		b1 = fmod(a1 - b3, MULTIPLIER) + 7;
		b2 = fmod(c2 - d5, MULTIPLIER) + 8;
		b3 = fmod(h3 - e3, MULTIPLIER) + 9;
		b4 = fmod(f2 - a4, MULTIPLIER) + 2;
		b5 = fmod(b2 - d5, MULTIPLIER) + 3;
		c1 = fmod(d1 - e5, MULTIPLIER) + 4;
		c2 = fmod(h2 - f2, MULTIPLIER) + 5;
		c3 = fmod(d4 - a3, MULTIPLIER) + 6;
		c4 = fmod(d4 - c1, MULTIPLIER) + 7;
		c5 = fmod(f1 - f5, MULTIPLIER) + 8;
		d1 = fmod(a4 - a1, MULTIPLIER) + 9;
		d2 = fmod(g2 - f3, MULTIPLIER) + 2;
		d3 = fmod(a1 - b5, MULTIPLIER) + 3;
		d4 = fmod(b4 - h4, MULTIPLIER) + 4;
		d5 = fmod(c5 - b1, MULTIPLIER) + 5;
		e1 = fmod(e2 - e3, MULTIPLIER) + 6;
		e2 = fmod(f3 - g2, MULTIPLIER) + 7;
		e3 = fmod(h1 - h3, MULTIPLIER) + 8;
		e4 = fmod(g1 - a4, MULTIPLIER) + 9;
		e5 = fmod(b1 - c5, MULTIPLIER) + 2;
		f1 = fmod(g3 - h5, MULTIPLIER) + 3;
		f2 = fmod(g4 - h1, MULTIPLIER) + 4;
		f3 = fmod(e3 - e5, MULTIPLIER) + 5;
		f4 = fmod(g1 - h4, MULTIPLIER) + 6;
		f5 = fmod(b5 - c2, MULTIPLIER) + 7;
		g1 = fmod(b1 - e2, MULTIPLIER) + 8;
		g2 = fmod(h2 - e3, MULTIPLIER) + 9;
		g3 = fmod(h3 - e4, MULTIPLIER) + 2;
		g4 = fmod(c2 - e5, MULTIPLIER) + 3;
		g5 = fmod(h5 - e5, MULTIPLIER) + 4;
		h1 = fmod(a1 - b1, MULTIPLIER) + 5;
		h2 = fmod(e2 - f5, MULTIPLIER) + 6;
		h3 = fmod(f3 - f2, MULTIPLIER) + 7;
		h4 = fmod(a4 - a1, MULTIPLIER) + 8;
		h5 = fmod(e5 - h5, MULTIPLIER) + 9;

	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> double_minus = duration_cast<duration<double>>(t2 - t1);

	Double.Minus = FIRST_DIVIDER * double(ITER) / (double_minus.count() - double_empty.count());

#pragma endregion
#pragma region DOUBLE*

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = fmod(b1 * c2, MULTIPLIER) + 2;
		a2 = fmod(b2 * d2, MULTIPLIER) + 3;
		a3 = fmod(a4 * c3, MULTIPLIER) + 4;
		a4 = fmod(f4 * d5, MULTIPLIER) + 5;
		a5 = fmod(b5 * g3, MULTIPLIER) + 6;
		b1 = fmod(a1 * b3, MULTIPLIER) + 7;
		b2 = fmod(c2 * d5, MULTIPLIER) + 8;
		b3 = fmod(h3 * e3, MULTIPLIER) + 9;
		b4 = fmod(f2 * a4, MULTIPLIER) + 2;
		b5 = fmod(b2 * d5, MULTIPLIER) + 3;
		c1 = fmod(d1 * e5, MULTIPLIER) + 4;
		c2 = fmod(h2 * f2, MULTIPLIER) + 5;
		c3 = fmod(d4 * a3, MULTIPLIER) + 6;
		c4 = fmod(d4 * c1, MULTIPLIER) + 7;
		c5 = fmod(f1 * f5, MULTIPLIER) + 8;
		d1 = fmod(a4 * a1, MULTIPLIER) + 9;
		d2 = fmod(g2 * f3, MULTIPLIER) + 2;
		d3 = fmod(a1 * b5, MULTIPLIER) + 3;
		d4 = fmod(b4 * h4, MULTIPLIER) + 4;
		d5 = fmod(c5 * b1, MULTIPLIER) + 5;
		e1 = fmod(e2 * e3, MULTIPLIER) + 6;
		e2 = fmod(f3 * g2, MULTIPLIER) + 7;
		e3 = fmod(h1 * h3, MULTIPLIER) + 8;
		e4 = fmod(g1 * a4, MULTIPLIER) + 9;
		e5 = fmod(b1 * c5, MULTIPLIER) + 2;
		f1 = fmod(g3 * h5, MULTIPLIER) + 3;
		f2 = fmod(g4 * h1, MULTIPLIER) + 4;
		f3 = fmod(e3 * e5, MULTIPLIER) + 5;
		f4 = fmod(g1 * h4, MULTIPLIER) + 6;
		f5 = fmod(b5 * c2, MULTIPLIER) + 7;
		g1 = fmod(b1 * e2, MULTIPLIER) + 8;
		g2 = fmod(h2 * e3, MULTIPLIER) + 9;
		g3 = fmod(h3 * e4, MULTIPLIER) + 2;
		g4 = fmod(c2 * e5, MULTIPLIER) + 3;
		g5 = fmod(h5 * e5, MULTIPLIER) + 4;
		h1 = fmod(a1 * b1, MULTIPLIER) + 5;
		h2 = fmod(e2 * f5, MULTIPLIER) + 6;
		h3 = fmod(f3 * f2, MULTIPLIER) + 7;
		h4 = fmod(a4 * a1, MULTIPLIER) + 8;
		h5 = fmod(e5 * h5, MULTIPLIER) + 9;

	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> double_multiply = duration_cast<duration<double>>(t2 - t1);

	Double.Multiply = FIRST_DIVIDER * double(ITER) / (double_multiply.count() - double_empty.count());

#pragma endregion
#pragma region DOUBLE/

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b1 + 2;
		a2 = b2 + 3;
		a3 = a4 + 4;
		a4 = f4 + 5;
		a5 = b5 + 6;
		b1 = a1 + 7;
		b2 = c2 + 8;
		b3 = h3 + 9;
		b4 = f2 + 2;
		b5 = b2 + 3;
		c1 = d1 + 4;
		c2 = h2 + 5;
		c3 = d4 + 6;
		c4 = d4 + 7;
		c5 = f1 + 8;
		d1 = a4 + 9;
		d2 = g2 + 2;
		d3 = a1 + 3;
		d4 = b4 + 4;
		d5 = c5 + 5;
		e1 = e2 + 6;
		e2 = f3 + 7;
		e3 = h1 + 8;
		e4 = g1 + 9;
		e5 = b1 + 2;
		f1 = g3 + 3;
		f2 = g4 + 4;
		f3 = e3 + 5;
		f4 = g1 + 6;
		f5 = b5 + 7;
		g1 = b1 + 8;
		g2 = h2 + 9;
		g3 = h3 + 2;
		g4 = c2 + 3;
		g5 = h5 + 4;
		h1 = a1 + 5;
		h2 = e2 + 6;
		h3 = f3 + 7;
		h4 = a4 + 8;
		h5 = e5 + 9;
	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> double_divide_empty = duration_cast<duration<double>>(t2 - t1);

	t1 = std::chrono::high_resolution_clock::now();
	for (long i = 0; i < ITER; ++i)
	{
		a1 = b1 / c2 + 2;
		a2 = b2 / d2 + 3;
		a3 = a4 / c3 + 4;
		a4 = f4 / d5 + 5;
		a5 = b5 / g3 + 6;
		b1 = a1 / b3 + 7;
		b2 = c2 / d5 + 8;
		b3 = h3 / e3 + 9;
		b4 = f2 / a4 + 2;
		b5 = b2 / d5 + 3;
		c1 = d1 / e5 + 4;
		c2 = h2 / f2 + 5;
		c3 = d4 / a3 + 6;
		c4 = d4 / c1 + 7;
		c5 = f1 / f5 + 8;
		d1 = a4 / a1 + 9;
		d2 = g2 / f3 + 2;
		d3 = a1 / b5 + 3;
		d4 = b4 / h4 + 4;
		d5 = c5 / b1 + 5;
		e1 = e2 / e3 + 6;
		e2 = f3 / g2 + 7;
		e3 = h1 / h3 + 8;
		e4 = g1 / a4 + 9;
		e5 = b1 / c5 + 2;
		f1 = g3 / h5 + 3;
		f2 = g4 / h1 + 4;
		f3 = e3 / e5 + 5;
		f4 = g1 / h4 + 6;
		f5 = b5 / c2 + 7;
		g1 = b1 / e2 + 8;
		g2 = h2 / e3 + 9;
		g3 = h3 / e4 + 2;
		g4 = c2 / e5 + 3;
		g5 = h5 / e5 + 4;
		h1 = a1 / b1 + 5;
		h2 = e2 / f5 + 6;
		h3 = f3 / f2 + 7;
		h4 = a4 / a1 + 8;
		h5 = e5 / h5 + 9;

	}
	t2 = std::chrono::high_resolution_clock::now();
	duration<double> double_divide = duration_cast<duration<double>>(t2 - t1);


	Double.Divide = FIRST_DIVIDER * double(ITER) / (double_divide.count() - double_divide_empty.count());

#pragma endregion

	SetMax(Double);

	Show("+", "double", Double.Plus, Double.Max);
	Show("-", "double", Double.Minus, Double.Max);
	Show("*", "double", Double.Multiply, Double.Max);
	Show("/", "double", Double.Divide, Double.Max);
	cout << "\n";
}

