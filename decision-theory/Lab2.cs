using System;
using System.Collections.Generic;
using System.Linq;

namespace DecisionTheory{
    public class Lab2{
        public static void Main(string[] args)
        {
            // PART 1
            Console.WriteLine("Завдання 1\n");
            Matrix<double> A = new Matrix<double>(
                rowsCount:12,
                columnsCount:8,
                elements: new double[]{
                    5, 3, 1, 2, 8, 4, 6, 7,
                    5, 4, 3, 1, 8, 2, 6, 7,
                    1, 7, 5, 4, 8, 2, 3, 6,
                    6, 4, 2.5, 2.5, 8, 1, 7, 5,
                    8, 2, 4, 6, 3, 5, 1, 7,
                    5, 6, 4, 3, 2, 1, 7, 8,
                    6, 1, 2, 3, 5, 4, 8, 7,
                    6, 1, 3, 2, 7, 4, 6, 8,
                    5, 1, 3, 2, 5, 4, 7, 8,
                    5, 3, 2, 1, 8, 4, 6, 7,
                    7, 1, 3, 2, 6, 4, 5, 8,
                    1, 6, 5, 3, 8, 4, 2, 7,
                }
            );

            // загальне впорядкування за середніми арифметичними рангів
            Vector<double> rankSum = A.GetColumnsSum();
            rankSum.PrintAsRow(withHeader: "Сума рангів                                 ");

            Vector<double> averageRank = (1f / A.rowsCount) * rankSum;
            averageRank.Round(toDigits: 3).PrintAsRow(withHeader: "Середнє арифметичне рангів                  ");

            Vector<int> averageRanking = averageRank.GetRanking();
            averageRanking.PrintAsRow(withHeader: "Підсумковий ранг по середньому арифметичному");

            // загальне впорядкування за медіанами рангів
            Vector<double> medians = A.GetColumnsMedian();
            medians.PrintAsRow(withHeader: "Медіани рангів                              ");

            Vector<int> medianRanking = medians.GetRanking();
            medianRanking.PrintAsRow(withHeader: "Підсумковий ранг по медіанах                ");

            // узгоджене ранжування
            Console.WriteLine();
            ClasteredRanking averageCR = new ClasteredRanking(withVectorRanking: averageRanking);
            ClasteredRanking medianCR = new ClasteredRanking(withVectorRanking: medianRanking);
            
            averageCR.Print(withHeader: "Кластеризоване ранжування середніх    ", withLetters: true);
            medianCR.Print(withHeader: "Кластеризоване ранжування медіан      ", withLetters: true);

            ClasteredRanking reconciliedRanking = ClasteredRanking.GetReconcilicationRanking(averageCR, medianCR);
            reconciliedRanking.Print(withHeader: "Упорядковане кластеризоване ранжування", withLetters: true);

            // ядро суперечностей
            ClasteredRanking.PrintContradictionCore(averageCR, medianCR, withHeader: "Ядро суперечностей", withLetters: true);

            // PART 2
            Console.WriteLine("\nЗавдання 2");
            ClasteredRanking[] clasteredRankings = new ClasteredRanking[] {
                new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){1},new List<int>(){2,3}, new List<int>(){4}, new List<int>(){5}, new List<int>(){6,7}
                }),
                new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){1, 3},new List<int>(){4}, new List<int>(){2}, new List<int>(){5}, new List<int>(){7}, new List<int>(){6},
                }),
                new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){1},new List<int>(){4}, new List<int>(){2}, new List<int>(){3}, new List<int>(){6}, new List<int>(){5}, new List<int>(){7}
                }),
                new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){1},new List<int>(){2, 4}, new List<int>(){3}, new List<int>(){5}, new List<int>(){7}, new List<int>(){6}
                }),
                new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){2},new List<int>(){3}, new List<int>(){4}, new List<int>(){5}, new List<int>(){1}, new List<int>(){6}, new List<int>(){7}
                }),
                new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){1},new List<int>(){3}, new List<int>(){2}, new List<int>(){5}, new List<int>(){6}, new List<int>(){7}, new List<int>(){4}
                }),
                new ClasteredRanking(withClusteredRanking: new List<List<int>>(){
                    new List<int>(){1},new List<int>(){5}, new List<int>(){3}, new List<int>(){4}, new List<int>(){2}, new List<int>(){6}, new List<int>(){7}
                })
            };

            Console.WriteLine("Приклад знаходження відстані Кемені для перших двох ранжувань");
            ClasteredRanking first = clasteredRankings[0];
            ClasteredRanking second = clasteredRankings[1];
            Matrix<int> firstMR = first.ranking.matrixRanking;
            Matrix<int> secondMR = second.ranking.matrixRanking;

            first.Print(withHeader:"\nПерше ранжування");
            firstMR.Print(withHeader:"Бінарна матриця відношень першого ранжування");
            second.Print(withHeader:"\nДруге ранжування");
            secondMR.Print(withHeader:"Бінарна матриця відношень другого ранжування");
            Console.WriteLine("\nВідстань Кемені між цими двома ранжуваннями: " + Kemeny.Distance(first, second));

            // відстань Кемені між ранжуваннями
            Matrix<int> kemenyDistance = Kemeny.DistanceAll(clasteredRankings);
            kemenyDistance.Print(withHeader: "Відстань Кемені між усіма ранжуваннями");

            // медіана Кемені-Снелла
            ClasteredRanking kemenyMedian = Kemeny.Median(clasteredRankings);
            kemenyMedian.Print(withHeader:"Медіана Кемені");
        }
    }
}