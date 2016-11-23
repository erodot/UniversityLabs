using System;

namespace lab3
{
	class MainClass
	{
		private static uint readUInt()
		{
			uint n;
			try
			{
				Console.Write("Enter matrix size: ");
				n = UInt32.Parse(Console.ReadLine());
			}
			catch (Exception ex)
			{
				Console.WriteLine("Error: " + ex.Message + "\n");
				return readUInt();
			}

			return n;

		}

		private static double computeNorm(double[][] M, double[] x, double[] d, int n)
		{
			double error = 0;
			double rowerr = 0;
			for (int i = 0; i < n; i++, rowerr=0){
				for (int j = 0; j < n; j++)
					rowerr += M[i][j] * x[j];
				rowerr -= d[i];
				error += rowerr * rowerr;
			}

			return Math.Sqrt(error);
		}

		public static void Main(string[] args)
		{
			// reading matrix size
			int n = (int)readUInt();

			// creating all arrays
			double[] a = new double[n];
			double[] b = new double[n];
			double[] c = new double[n];
			double[] c_new = new double[n];
			double[] d = new double[n];
			double[] d_new = new double[n];
			double[] x = new double[n];
			double[][] M = new double[n][];
			for (int i = 0; i < n; i++)
				M[i] = new double[n];

			// inizializing all arrays
			Random rand = new Random();
			for (int i = 0; i < n; i++){
				//a[i] = rand.NextDouble();
				//b[i] = rand.NextDouble();
				//c[i] = rand.NextDouble();
				//d[i] = rand.NextDouble();

				a[i] = Math.IEEERemainder(rand.Next(), 100);
				b[i] = Math.IEEERemainder(rand.Next(), 100);
				c[i] = Math.IEEERemainder(rand.Next(), 100);
				d[i] = Math.IEEERemainder(rand.Next(), 100);

				M[i][i] = b[i];
				if (i != 0)
					M[i][i - 1] = a[i];
				if (i != n - 1)
					M[i][i + 1] = c[i];
			}

			// render matrix
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
					Console.Write(M[i][j] + " ");
				Console.WriteLine("= " + d[i]);
			}

			// first step of algorithm
			c_new[0] = c[0] / b[0];
			if (n > 1) 
				for (int i = 1; i < n - 1; i++)
					c_new[i] = c[i] / (b[i] - c_new[i-1]*a[i]);

			// second step of algorithm
			d_new[0] = d[0] / b[0];
			if (n > 1) 
				for (int i = 1; i < n; i++)
					d_new[i] = (d[i] - d_new[i-1]*a[i]) / (b[i] - c_new[i-1]*a[i]);

			// third step of algorithm
			x[n - 1] = d_new[n - 1];
			if (n > 1)
				for (int i = n - 2; i >= 0; i--)
					x[i] = d_new[i] - c_new[i] * x[i + 1];

			// show aswer
			Console.Write("\nx = [ ");
			for (int i = 0; i < n - 1; i++)
				Console.Write(x[i] + ", ");
			Console.WriteLine(x[n - 1] + "]");

			//show error
			Console.WriteLine("||Mx-d|| = " + computeNorm(M, x, d, n));
		}
	}
}
