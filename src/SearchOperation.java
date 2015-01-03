/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:45
 */
public class SearchOperation implements Operation{
    private MetricSet<AsciiImage> saved;
    private Metric<AsciiImage> metric;


    public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> metric) {
        this.saved = saved;
        this.metric = metric;
    }

    public AsciiImage execute(AsciiImage img) throws OperationException {
        MetricSet<AsciiImage> results = saved.search(img,metric);
        if(saved.size() == 0)
            throw new OperationException("No saved images");
        //return the first image if more than one is found
        return results.iterator().next();
    }
}
