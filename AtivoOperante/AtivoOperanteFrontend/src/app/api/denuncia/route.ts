import { NextResponse } from 'next/server';

export async function POST(request: Request) {
    try {
        const token = request.headers.get('cookie')?.split('; ')
            .find(row => row.startsWith('token='))?.split('=')[1];

        console.log('Token:', token);
        const body = await request.json();
        console.log('Request body:', body);

        const response = await fetch('http://localhost:8080/api/denuncia', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body)
        });

        if (!response.ok) {
            const errorData = await response.text();
            console.error('Backend error response:', errorData);
            console.error('Response status:', response.status);
            console.error('Response headers:', Object.fromEntries(response.headers.entries()));
            return NextResponse.json(
                { message: 'Failed to create denuncia', details: errorData },
                { status: response.status }
            );
        }

        const data = await response.json();
        return NextResponse.json(data);
    } catch (error) {
        console.error('Error creating denuncia:', error);
        return NextResponse.json(
            { message: 'Failed to create denuncia', details: error instanceof Error ? error.message : 'Unknown error' },
            { status: 500 }
        );
    }
}
