import React, {useEffect, useState} from 'react';
import {Button, Container, Dimmer, Loader, Placeholder} from 'semantic-ui-react';
import * as main from 'kotlinmultiapp/packages/kotlinmultiapp-common'
import './QuotePage.scss';

function QuotePage() {

    const [quoteModel, setQuoteModel] = useState({loading: true});
    let viewModel = main.de.kneke.common.injectJs.quoteViewModel();

    useEffect(() => {
        function receiveNewQuote(newQuoteModel) {
            setQuoteModel(newQuoteModel);
        }

        viewModel.quoteModel.watch_qlkmfe$(receiveNewQuote);
        loadNextQuote();

        return function cleanup() {
            viewModel.quoteModel.unwatch();
        };
    }, []);

    function loadNextQuote() {
        viewModel.get_6taknv$(true);
    }
    return (
        <div className="quote-page">
            {quoteModel.loading &&
            <Dimmer active inverted>
                <Loader inverted>Loading</Loader>
            </Dimmer>
            }
            {quoteModel.quote &&
            <Container text textAlign='center'>
                <p>{quoteModel.quote.quote}</p>
                <p>{quoteModel.quote.author}</p>
                <Button primary className="quote-button" onClick={loadNextQuote}>Next</Button>
            </Container>
            }
        </div>
    );
}

export default QuotePage;
