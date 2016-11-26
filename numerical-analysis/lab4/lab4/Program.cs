using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace lab4
{
	class MainClass
	{
		public const int ITERATIONS_NUM = 100000;
		public const string TEXTFILE_PATH = @"/Users/tedromanus/Workspace/UniversityLabs/numerical-analysis/lab4/lab4/webpages_graph.txt";

		// demping value
		public const double d = 0.85;

		public static void Main(string[] args)
		{
			// number of webpages
			int n;

			// quotation matrix: count of quotations of j page on page i 
			int[,] QM;

			// reading info from textfile
			var fileStream = new FileStream(TEXTFILE_PATH, FileMode.Open, FileAccess.Read);
			using (var streamReader = new StreamReader(fileStream))
			{
				string line;

				// reading number of webpages
				line = streamReader.ReadLine();
				n = Int32.Parse(line);

				// reading links from page a to page b in format "a b"
				QM = new int[n, n];
				while ((line = streamReader.ReadLine()) != null)
				{
					var link = line.Split(' ');
					var i = Int32.Parse(link[0]);
					var j = Int32.Parse(link[1]);
					QM[i, j]++;
				}
			}

			// count of quotations on page #i
			int[] C = new int[n];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					C[i] += QM[i, j];

			// initial pagerank - all values are equal with sum of 100%
			double[] PR = new double[n];
			for (int i = 0; i < n; i++)
				PR[i] = 1.0;

			// iteration cycle
			for (int k = 0; k < ITERATIONS_NUM; k++)
			{
				for (int j = 0; j < n; j++)
				{
					double sum = 0;
					for (int i = 0; i < n; i++)
					{
						if (C[i] == 0)
							continue;
						sum += QM[i, j] * PR[i] / C[i];
					}
					PR[j] = (1 - d) + d * sum;
				}
			}

			double norm = 0;
			for (int i = 0; i < n; i++)
				norm += PR[i];

			// normalizing pageranks to sum equal 1
			for (int i = 0; i < n; i++)
				PR[i] /= norm;

			// show answers
			for (int i = 0; i < n; i++)
				Console.WriteLine(i + ": " + String.Format("{0:0.##}", 100 * PR[i]) + "%");
		}
	}
}
