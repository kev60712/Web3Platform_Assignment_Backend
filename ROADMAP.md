1. Go through every dataset and build a service to covert every dataset to one table, because I think all the dataset are related to compensation record.
2. Identify what kind of query user use the most, and set up index to increse query performance.
3. Discuss api response format.
4. Based on the use case, maybe we should have rate limit for api.
5. If the frontend need quick response, maybe we can use some cache technique or redis to prevent query database all the time.
