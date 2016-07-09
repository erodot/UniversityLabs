#pragma once

#include <iostream>
#include <string>

#define N 16

class Register 
{
public:
	Register();

	std::string GetValue();
	void SetValue(std::string);
	int value[N];
	std::string ValueToString();

private:
	void InitializeComponents();
};

class Command 
{
public:
	Command():R0(new Register()), R1(new Register()), R2(new Register()), 
		instructionAmount(0), tactAmount(0), lastChar('+'), overflow(0) {}

	void ShowRegisters();
	void SetValue(std::string,std::string);
	void Add(std::string, std::string);
	void Subtract(std::string, std::string);
	void Invert(std::string);
	void Invert(Register*);

private:
	Register* R0;
	Register* R1;
	Register* R2;

	char lastChar;
	int instructionAmount;
	int tactAmount;
	int overflow;
};
