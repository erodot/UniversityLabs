#include <iostream>
#include "auxiliary.h"
#include "classes.h"

int main()
{
	Command* cmd = new Command();

	std::string inputString;
	while (inputString != "exit")
	{
		std::getline(std::cin, inputString);
		std::vector<std::string> inputStringVector = split(inputString, ' ');
		
		if (inputStringVector.size() == 1 && inputStringVector[0] == "show")
		{
			cmd->ShowRegisters();
		}
		else if (inputStringVector.size() == 3 && inputStringVector[0] == "set")
		{
			cmd->SetValue(inputStringVector[1], inputStringVector[2]);
		}
		else if (inputStringVector.size() == 3 && inputStringVector[0] == "add")
		{
			cmd->Add(inputStringVector[1], inputStringVector[2]);
		}
		else if (inputStringVector.size() == 3 && inputStringVector[0] == "subtract")
		{
			cmd->Subtract(inputStringVector[1], inputStringVector[2]);
		}
		else if (inputStringVector.size() == 2 && inputStringVector[0] == "invert")
		{
			cmd->Invert(inputStringVector[1]);
		}
		else
		{
			std::cout << "incorrect command\n";
		}

	}
	return 0;
}