import React, {useEffect, useState} from 'react';
import {Button, Container, Dimmer, Loader} from 'semantic-ui-react';
import * as main from 'kotlinmultiapp/packages/kotlinmultiapp-common'
import './QuotePage.css';

function QuotePage() {

    const [quote, setQuote] = useState(null);

    let viewModel = main.de.kneke.common.injectJs.quoteViewModel();

    useEffect(() => {
        function receiveNewQuote(resource) {
            setQuote(resource.data);
        }

        viewModel.quoteResource.watch_qlkmfe$(receiveNewQuote);
        loadNextQuote();

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
