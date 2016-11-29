using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using System.Linq;

namespace lab1_3_lexer
{
	class Program
	{
		enum TokenType
		{
			Comparison,
			Punct,
			If,
			Else,
			Ident, // должно идти после ключевых слов!
			NumLiteral
		};

		class Token
		{
			public TokenType Type;
			public string StringValue;
			public double NumericValue;
			// сюда стоит ещё добавить информацию о позиции в исходном тексте
		}

		// регулярки, описывающие лексему, начинаются с ^, чтобы матчить только в начале
		Dictionary<TokenType, Regex> regexes = new Dictionary<TokenType, Regex>()
		{
			[TokenType.Comparison] = new Regex(@"^(>=?|<=?|==)", RegexOptions.Compiled),
			[TokenType.Punct] = new Regex(@"^[,)(}{;]", RegexOptions.Compiled),
			[TokenType.If] = new Regex(@"^if", RegexOptions.Compiled),
			[TokenType.Else] = new Regex(@"^else", RegexOptions.Compiled),
			[TokenType.Ident] = new Regex(@"^[a-zA-Z_][a-zA-Z_0-9]*", RegexOptions.Compiled),
			[TokenType.NumLiteral] = new Regex(@"^[0-9]+(\.[0-9]+)?", RegexOptions.Compiled)
		};

		// что ещё записать в токен?
		Dictionary<TokenType, Action<string, Token>> creators =
			new Dictionary<TokenType, Action<string, Token>>()
			{
				[TokenType.Comparison] = (s, token) => token.StringValue = s,
				[TokenType.Punct] = (s, token) => token.StringValue = s,
				[TokenType.If] = (s, token) => { },
				[TokenType.Else] = (s, token) => { },
				[TokenType.Ident] = (s, token) => token.StringValue = s,
				[TokenType.NumLiteral] = (s, token) => token.NumericValue = double.Parse(s)
			};

		// а вот и вся логика:
		IEnumerable<Token> Tokenize(string text)
		{
			// откусываем пробелы
			var remainingText = text.TrimStart();
			while (remainingText != "")
			{
				// находим наиболее подходящий паттерн:
				var bestMatch =
				   (from pair in regexes
					let tokenType = pair.Key
					let regex = pair.Value
					let match = regex.Match(remainingText)
					let matchLen = match.Length
				// упорядочиваем по длине, а если длина одинаковая - по типу токена
				orderby matchLen descending, tokenType
					select new { tokenType, text = match.Value, matchLen }).First();

				// если везде только 0, ошибка
				if (bestMatch.matchLen == 0)
					throw new Exception("Unknown lexeme");

				var token = new Token() { Type = bestMatch.tokenType };
				creators[bestMatch.tokenType](bestMatch.text, token);
				yield return token;

				// откусываем распознанный кусок и пробелы после него
				remainingText = remainingText.Substring(bestMatch.matchLen).TrimStart();
			}
		}

		static void Main(string[] args)
		{
			new Program().Run();
		}

		void Run()
		{
			var text = "else if if x > < > if == else; 25.0 7";
			foreach (var token in Tokenize(text))
				Console.WriteLine(token.Type);
		}
	}
}
