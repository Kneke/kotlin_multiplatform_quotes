import React, {useEffect, useState} from 'react';
import {Button, Container, Dimmer, Loader} from 'semantic-ui-react';
import * as main from 'kotlinmultiapp/packages/kotlinmultiapp-common'
import './QuotePage.css';

function QuotePage() {

    const [quote, setQuote] = useState({
        "quote": "Good Source Code makes me horny.",
        "id": 45,
        "author": "Christoph Knetschke",
        "permalink": "http://quotes.stormconsultancy.co.uk/quotes/45"
    },[]);

    let viewModel = main.de.kneke.common.quoteViewModel();

    useEffect(() => {
        function receiveNewQuote(resource) {
            setQuote(resource.data);
        }

        viewModel.quoteResource.watch_qlkmfe$(receiveNewQuote);

        return function cleanup() {
            viewModel.quoteResource.unwatch();
        };
    },[]);

    function loadNextQuote() {
        viewModel.get_6taknv$(true);
    }

    return (
        <div className="quote-page">
            {quote == null ?
                <Dimmer active inverted>
                    <Loader inverted>Loading</Loader>
                </Dimmer>
                :
                <Container text>
                    <p>{quote.quote}</p>
                    <p>{quote.author}</p>
                    <Button onClick={loadNextQuote}>Next</Button>
                </Container>
            }
        </div>
    );
}

export default QuotePage;
