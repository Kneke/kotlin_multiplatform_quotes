import React, {useEffect, useState} from 'react';
import {Button, Container, Dimmer} from 'semantic-ui-react';
import Lottie from 'react-lottie';
import * as loadingAnimation from '../assets/loading_message_lottie';
import * as main from 'kotlinmultiapp/packages/kotlinmultiapp-common';
import './QuotePage.scss';

function QuotePage() {

    let viewModel = main.de.kneke.common.injectJs.quoteViewModel();

    const [quoteModel, setQuoteModel] = useState({loading: true});

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
                <Lottie options={{
                    loop: true,
                    autoplay: true,
                    animationData: loadingAnimation.default,
                    rendererSettings: {preserveAspectRatio: 'xMidYMid slice'}
                }}/>
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
