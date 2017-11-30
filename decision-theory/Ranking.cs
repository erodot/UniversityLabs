using System;
using System.Collections.Generic;
using System.Linq;

namespace DecisionTheory{
    public class Ranking{
        public Vector<int> vectorRanking { get; set; }

        public List<Tuple<int, List<int>>> tupleRanking { 
            get {
                List<Tuple<int, List<int>>> ranks = new List<Tuple<int, List<int>>>();
                for(int i = 1; i <= vectorRanking.ToList().Max(); i++)
                    ranks.Add(new Tuple<int, List<int>>(i, new List<int>()));
                
                for(int i=0; i<vectorRanking.length; i++){
                    ranks.First(t => t.Item1 == vectorRanking.get(i)).Item2.Add(i);
                }
                return ranks;
            }
            set {
                List<Tuple<int, int>> rankingTuple = new List<Tuple<int, int>>();
                foreach(Tuple<int, List<int>> rankList in value)
                    foreach(int rankListItem in rankList.Item2)
                        rankingTuple.Add(new Tuple<int,int>(rankList.Item1, rankListItem)); // first - rank, second - position
                
                rankingTuple.Sort((first, second) => first.Item2 > second.Item2 ? 1 : -1);
                List<int> elems = rankingTuple.Select(x => x.Item1).ToList();
                this.vectorRanking = new Vector<int>(elems);
            }
        }

        public List<List<int>> clusteredRanking {
            get {
                return tupleRanking.Select(tr => tr.Item2).ToList();
            }
            set {
                List<Tuple<int, List<int>>> ranks = new List<Tuple<int, List<int>>>();
                for(int i=0; i < value.Count; i++){
                    ranks.Add(new Tuple<int, List<int>>(i+1, value[i]));
                }
                this.tupleRanking = ranks;
            }
        }

        public Matrix<int> matrixRanking {
            get {
                List<int> vectRanking = new List<int>();
                for(int i =0; i<vectorRanking.length; i++)
                    for(int j=0; j<vectorRanking.length; j++)
                        vectRanking.Add(vectorRanking.get(i) <= vectorRanking.get(j) ? 1 : 0);

                return new Matrix<int>(vectorRanking.length, vectorRanking.length, vectRanking.ToArray());
            }
        }
    }
}