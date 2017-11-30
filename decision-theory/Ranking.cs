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
            set{
                Matrix<int> binaryRelations = value;
                List<List<int>> groups = new List<List<int>>();
                for(int i=0; i<binaryRelations.rowsCount; i++)
                    groups.Add(new List<int>(){i+1});

                // get eq classes
                for(uint i=0; i< binaryRelations.rowsCount; i++)
                    for(uint j=0; j< binaryRelations.rowsCount; j++)
                        if(i != j && binaryRelations.get(i, j) == 1 && binaryRelations.get(j, i) == 1) {
                            // elements are in the same eq class
                            int firstListIndex = default(int), secondListIndex = default(int);
                            for(int k = 0; k<binaryRelations.rowsCount; k++){
                                Console.WriteLine(k);
                                if(groups[k].Contains((int)(i+1)))
                                    firstListIndex = k;
                                else if(groups[k].Contains((int)(j+1)))
                                    secondListIndex = k;
                            }
                            
                            if(firstListIndex != secondListIndex){
                                List<int> first = groups[firstListIndex];
                                List<int> second = groups[secondListIndex];
                                groups.Remove(first);
                                groups.Remove(second);

                                List<int> union = new List<int>();
                                union.AddRange(first);
                                union.AddRange(second);
                            }
                        }
                
                // now groups contains all eq classes
                List<Tuple<int, List<int>>> groupsTuple = new List<Tuple<int, List<int>>>();
                for(int i = 0; i< groups.Count; i++)
                    groupsTuple.Add(new Tuple<int, List<int>>(1, groups[i]));
                
                for(int i = 0; i < groupsTuple.Count; i++)
                    for(int j = 0; j < groupsTuple.Count; j++)
                        if(i!=j){
                            uint itemFromFirstClaster = (uint)(groupsTuple[i].Item2[0]-1);
                            uint itemFromSecondClaster = (uint)(groupsTuple[j].Item2[0]-1);
                            if(binaryRelations.get(itemFromFirstClaster, itemFromSecondClaster) == 1)
                                groupsTuple[j] = new Tuple<int, List<int>>(groupsTuple[j].Item1+1, groupsTuple[j].Item2);
                            else 
                                groupsTuple[i] = new Tuple<int, List<int>>(groupsTuple[i].Item1+1, groupsTuple[i].Item2);
                        }

                this.tupleRanking = groupsTuple;
            }
        }
    }
}