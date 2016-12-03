using System;
using System.IO;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using System.Linq;

namespace lab1_3_lexer
{
	class Program
	{
		const string path = @"/Users/tedromanus/Workspace/UniversityLabs/system-programming/lab1_3_lexer/lab1_3_lexer/snippet.txt";

		enum TokenType
		{
			Keyword, // ключові слова
			Hex, // шістнадцяткові цілі числа
			Identifier, // ідентифікатори
			Int, // десяткові цілі числа
			Float, // десяткові дійсні числа
			String, // рядкові константи
			Char, // символьні константи
			Comment, // коментарі
			Directive, // директиви препроцесора
			Operator, // оператори
			Punctuation, // розділові знаки
			Error // помилки
		};

		class Token
		{
			public TokenType Type;
			public string Value;
			public int Length;
		}

		// searching in the beginning of string
		Dictionary<TokenType, Regex> regexes = new Dictionary<TokenType, Regex>()
		{
			[TokenType.Keyword] = new Regex(@"^(abstract|event|int|new|struct|as|explicit|null|switch|base|extern|object|this|bool|false|operator|throw|break|finally|out|true|byte|fixed|override|try|case|float|params|typeof|catch|for|private|uint|char|foreach|protected|ulong|checked|goto|public|unchecked|class|if|readonly|unsafe|const|implicit|ref|ushort|continue|in|return|using|decimal|sbyte|virtual|default|interface|sealed|volatile|delegate|internal|short|void|do|is|sizeof|while|double|lock|stackalloc|else|long|static|enum|namespace|string)", RegexOptions.Compiled),
			[TokenType.Hex] = new Regex(@"^0[xX]((0|[1-9a-fA-F][\da-fA-F]*)(UL|Ul|uL|ul|LU|Lu|lU|lu|U|u|L|l)?)", RegexOptions.Compiled),
			[TokenType.Identifier] = new Regex(@"^@?([_\p{Lu}\p{Ll}\p{Lt}\p{Lm}\p{Lo}\p{Nl}][\p{Lu}\p{Ll}\p{Lt}\p{Lm}\p{Lo}\p{Nl}\p{Mn}\p{Mc}\p{Nd}\p{Pc}\p{Cf}]*)", RegexOptions.Compiled),
			[TokenType.Int] = new Regex(@"^((0|[1-9]\d*)(UL|Ul|uL|ul|LU|Lu|lU|lu|U|u|L|l)?)", RegexOptions.Compiled),
			[TokenType.Float] = new Regex(@"^(((0|[1-9]\d*)?\.\d+([eE][+-]?\d+)?[FfDdMm]?)|((0|[1-9]\d*)([eE][+-]?\d+)[FfDdMm]?)|((0|[1-9]\d*)[FfDdMm]))", RegexOptions.Compiled),
			[TokenType.String] = new Regex(@"^((""([^\""\n\r\\]*|\\[\\0abfnrtuUxv]|\\x[\da-fA-F]{1,4}|\\u[\da-fA-F]{4}|\\U[\da-fA-F]{8})*"")|(@""([^\""]|"""")*""))", RegexOptions.Compiled),
			[TokenType.Char] = new Regex(@"^('([^'\n\\]|\\['""\\0abfnrtuUxv]|\\x[\da-fA-F]{1,4}|\\u[\da-fA-F]{4}|\\U[\da-fA-F]{8})')", RegexOptions.Compiled),
			[TokenType.Comment] = new Regex(@"^(\/\/[^\n\r]*|\/\*(.|\n)*?\*\/)", RegexOptions.Compiled),
			[TokenType.Directive] = new Regex(@"^(#(if|else|elif|endif|define|undef|warning|error|line|region|endregion|pragma))", RegexOptions.Compiled),
			[TokenType.Operator] = new Regex(@"^(\?|[=!<>\+\-\*\/%&\|\^]=|<<=|>>=|<<?|>>?|->|\.|\+\+?|--?|\*|\/|%|&&?|\|\|?|\^|!|~|=)", RegexOptions.Compiled),
			[TokenType.Punctuation] = new Regex(@"^[{}\[\](),:;]", RegexOptions.Compiled)
		};

		// main logic
		IEnumerable<Token> Tokenize(string text)
		{
			// trim whitespaces
			var remainingText = text.TrimStart();
			String error = "";
			while (remainingText != "")
			{
				// looking for best matching
				var bestMatch =
				   (from pair in regexes
					let tokenType = pair.Key
					let regex = pair.Value
					let match = regex.Match(remainingText)
					let matchLength = match.Length
					orderby matchLength descending, tokenType
					select new { tokenType, value = match.Value, matchLength}).First();

				// no match
				if (bestMatch.matchLength == 0)
				{
					error += remainingText[0];
					remainingText = remainingText.Substring(1);
					continue;
				}
				else if (error != "")
				{
					error = error.Trim();
					var errtoken = new Token() { Type = TokenType.Error, Value = error, Length = error.Length };
					error = "";
					yield return errtoken;
				}

				var token = new Token() { Type = bestMatch.tokenType, Value = bestMatch.value, Length = bestMatch.matchLength };
				yield return token;

				// cut out proceeded lexeme
				remainingText = remainingText.Substring(bestMatch.matchLength).TrimStart();
			}
		}

		static void Main(string[] args)
		{
			new Program().Run();
		}

		void Run()
		{
			var text = File.ReadAllText(path);
			foreach (var token in Tokenize(text))
				Console.WriteLine(token.Value + " - " + token.Type);
		}
	}
}
