#include "classes.h"
#include <iostream>
#include <string>
#include <math.h>

Register::Register()
{
	this->InitializeComponents();
}

void Register::InitializeComponents()
{
	for (int i = 0; i < N; ++i)
		value[i] = 0;
}

void Register::SetValue(std::string value)
{
	int num = std::stoi(value);
	bool isMinus = (num < 0);
	if (isMinus)
	{
		num *= -1;
		this->value[0] = 1;
	}
	else
		this->value[0] = 0;
	for (int i = 0; i < N-1; ++i)
	{
		this->value[N-1-i] = (int)((num % ((int)pow(2, i+1))) / pow(2, i));
	}
	if (isMinus)
	{
		for (int i = 1; i < N; ++i)
			if (this->value[i] == 0)
				this->value[i] = 1;
			else
				this->value[i] = 0;
		++this->value[N - 1];
		for(int i=N-1;i>1;i--)
			if (this->value[i] > 1)
			{
				this->value[i] %= 2;
				++this->value[i - 1];
			}
		this->value[1] %= 2;
	}
}

std::string Register::ValueToString()
{
	std::string str = "";
	for (int i = 0; i < N; ++i)
		str += std::to_string(this->value[i]);
	return str;
}

std::string Register::GetValue()
{
	std::string str = "";
	for (int i = 0; i < N; ++i)
	{
		str += std::to_string(value[i]);
		if ((i+1) % 4 == 0)
			str += "  ";
	}
	return str;
}

void Command::ShowRegisters()
{
	std::cout << "REGISTERS";
	std::cout << "\nR0  " << this->R0->GetValue();
	std::cout << "\nR1  " << this->R1->GetValue();
	std::cout << "\nR2  " << this->R2->GetValue();
	std::cout << "\n\n";

	this->instructionAmount += 1;
	this->tactAmount += 6;

	std::cout << "last char: " << this->lastChar << '\n';
	std::cout << "instruction amount: " << this->instructionAmount << '\n';
	std::cout << "tact amount: " << this->tactAmount << '\n';
	std::cout << "overflow " << this->overflow << '\n';
	std::cout << '\n';
}

void Command::SetValue(std::string name, std::string value)
{
	if (name == "R0") R0->SetValue(value);
	else if (name == "R1") R1->SetValue(value);
	else if (name == "R2") R2->SetValue(value);
	else return;

	if (std::stoi(value) < 0) this->lastChar = '-';
	else this->lastChar = '+';

	++this->instructionAmount;
	this->tactAmount += 2;

	if (std::stoi(value) >= pow(2, N))
		++this->overflow;
}

void Command::Add(std::string _first, std::string _second)
{
	Register* first;
	Register* second;

	if (_first == "R0") first = this->R0;
	else if (_first == "R1") first = this->R1;
	else if (_first == "R2") first = this->R2;
	else return;

	if (_second == "R0") second = this->R0;
	else if (_second == "R1") second = this->R1;
	else if (_second == "R2") second = this->R2;
	else return;

	for (int i = N-1; i > 0; --i)
	{
		first->value[i] += second->value[i];
		if (first->value[i] > 1)
		{
			first->value[i] %= 2;
			++first->value[i - 1];
		}	
	}

	first->value[0] += second->value[0];
		if (first->value[0] > 1)
			this->overflow++;
		first->value[0] %= 2;

	this->lastChar = '+';

	++this->instructionAmount;
	this->tactAmount += 2;
}

void Command::Subtract(std::string _first, std::string _second)
{
	Register* first;
	Register* second;

	if (_first == "R0") first = this->R0;
	else if (_first == "R1") first = this->R1;
	else if (_first == "R2") first = this->R2;
	else return;

	if (_second == "R0") second = this->R0;
	else if (_second == "R1") second = this->R1;
	else if (_second == "R2") second = this->R2;
	else return;

	this->Invert(second);
	this->Add(_first, _second);
	this->Invert(second);

	this->lastChar = '-';

	++this->instructionAmount;
	this->tactAmount += 2;
}

void Command::Invert(std::string str)
{
	Register* reg = new Register();

	if (str == "R0") reg = this->R0;
	else if (str == "R1") reg = this->R1;
	else if (str == "R2") reg = this->R2;
	else return;

	this->Invert(reg);

	this->lastChar = '-';

	++this->instructionAmount;
}

void Command::Invert(Register* reg)
{
	//plus value
	if (reg->value[0] == 0)
	{
		for (int i = 0; i < N; ++i)
			if (reg->value[i] == 0)
				reg->value[i] = 1;
			else reg->value[i] = 0;
		++reg->value[N - 1];
		for (int i = N - 1; i>1; i--)
			if (reg->value[i] > 1)
			{
				reg->value[i] %= 2;
				++reg->value[i - 1];
			}
		reg->value[1] %= 2;
	}
	else
	{
		//minus value
		--reg->value[N - 1];
		reg->value[0] += 2;
		for (int i = N - 1; i>0; --i)
			if (reg->value[i] < 0)
			{
				reg->value[i] += 2;
				--reg->value[i - 1];
			}
		reg->value[1] %= 2;
		for (int i = 0; i < N; ++i)
			if (reg->value[i] == 0)
				reg->value[i] = 1;
			else reg->value[i] = 0;
	}

	this->tactAmount += 2;
}